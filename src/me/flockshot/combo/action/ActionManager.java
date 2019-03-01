package me.flockshot.combo.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.flockshot.combo.ComboPlugin;
import me.flockshot.combo.combo.ComboType;
import me.flockshot.combo.requirement.Requirement;
import me.flockshot.combo.subactionmanager.SubAction;
import me.flockshot.combo.utils.ItemUtility;

public class ActionManager
{
	private ComboPlugin plugin;	
	private List<Action> actions = new ArrayList<Action>();

	
	public ActionManager(ComboPlugin main)
	{
		plugin = main;
	}
	
	public void registerActions(File folder)
	{
		File[] files = folder.listFiles();
		
		for(File file : files)
		{
			List<Action> actionsFromFile = getActions(YamlConfiguration.loadConfiguration(file));
			actions.addAll(actionsFromFile);
		}
	}
	
	private List<Action> getActions(FileConfiguration file)
	{
		List<Action> acts = new ArrayList<Action>();		
		
		Set<String> names = file.getConfigurationSection("").getKeys(false);
		if(names!=null)
		{
			for(String name : names)
			{
				String itemID = file.contains(name+".Item") ? file.getString(name+".Item") : null;
				int data = file.contains(name+".Data") ? file.getInt(name+".Data") : 1;
				
				ItemUtility iUtil = new ItemUtility();
				ItemStack item = iUtil.generateItem(itemID, data);
				
				if(item==null)
				{
					plugin.getLogger().log(Level.SEVERE, ChatColor.DARK_RED+"Invalid Item of "+name+" in file"+file.getName());
					continue;
				}
				
				List<Requirement> globalReqs = plugin.getRequirementManager().getRequirements(file, name+".GlobalRequirements");
				List<SubAction> subActions = plugin.getSubActionManager().getSubActions(file, name+".SubActions");
				
				Action action = new Action(name, item, globalReqs, subActions);
				
				acts.add(action);
			}
		}
		//TODO
		return acts;		
	}


	public void callAction(ComboType type, Player player, ItemStack item, boolean physical)
	{		
		List<Action> finalActs = getFinalActs(item);

		for(Action act : finalActs)
		{
			SubAction subAct = act.getSubActionFromType(type);
			if(subAct!=null)
			{
				if(plugin.getRequirementManager().passesRequirements(player, item, act.getGlobalRequirements()))
				{					
					if(plugin.getRequirementManager().passesRequirements(player, item, physical, subAct.getRequirements()))
					{
						plugin.getRequirementManager().startCooldownTimer(act.getGlobalRequirements(), player);
						plugin.getRequirementManager().startCooldownTimer(subAct.getRequirements(), player);
						
						plugin.getExecutableManager().executeExecutables(player, subAct.getAcceptance());
					}
					return;
				}
			}							
		}
	}

	
	public boolean actionRegisteredToItem(Player player, ItemStack item)
	{
		List<Action> finalActs = getFinalActs(item);
		
		if(finalActs.size()>=1)
		{
			Action action = finalActs.get(0);
			if(action.getGlobalRequirements().stream().filter(req -> plugin.getRequirementManager().instanceOfItemRequirement(req)).count()>=1)
			{
				for(Requirement req : action.getGlobalRequirements().stream().filter(req -> plugin.getRequirementManager().instanceOfItemRequirement(req)).collect(Collectors.toList()))
				{
					if(!req.passesRequirement(player, item))
						return false;
				}		
			}
			return true;
		}
		return false;		
	}
	
	public List<Action> getFinalActs(ItemStack item)
	{
		return getActions().stream().filter(act -> act.getItem().getType().equals(item.getType())).filter(act -> act.getItem().getDurability()==item.getDurability()).collect(Collectors.toList());
	}
	
	private List<Action> getActions() {
		return actions;
	}
	
	
	
}
