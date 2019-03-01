package me.flockshot.combo.requirement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.flockshot.combo.ComboPlugin;
import me.flockshot.combo.executable.Executable;
import me.flockshot.combo.requirements.item.ItemLoreEquals;
import me.flockshot.combo.requirements.item.ItemLoreEqualsIgnoreCase;
import me.flockshot.combo.requirements.item.ItemNameContains;
import me.flockshot.combo.requirements.item.ItemNameContainsIgnoreCase;
import me.flockshot.combo.requirements.item.ItemNameEquals;
import me.flockshot.combo.requirements.item.ItemNameEqualsIgnoreCase;
import me.flockshot.combo.requirements.player.Cooldown;
import me.flockshot.combo.requirements.player.StriclyPhysical;

public class RequirementManager 
{
	ComboPlugin plugin;
	HashMap<String, Requirement> registered = new HashMap<String, Requirement>();
	
	public RequirementManager(ComboPlugin plugin)
	{
		this.plugin = plugin;
	}
	
	public List<Requirement> getRequirements(FileConfiguration file, String path)
	{
		List<Requirement> reqs = new ArrayList<Requirement>();

		if(file.contains(path))
		{
			//Set<String> pathNames = file.getConfigurationSection(path).getKeys(false);
			
			for(final String reqName : file.getConfigurationSection(path).getKeys(false))
			{
				String fullPath = path+"."+reqName;
				
				
				Requirement requirement = file.contains(fullPath+".Type") ? getFromIdentifier(file.getString(fullPath+".Type")) : null;					
				Object value = file.contains(fullPath+".Value") ? file.get(fullPath+".Value") : null;
				String compareWith = file.contains(fullPath+".CompareWith") ? file.getString(fullPath+".CompareWith") : "";
				
				if(value==null || requirement==null || !(value instanceof String || value instanceof List<?> || value instanceof Integer))
				{
					plugin.getLogger().log(Level.SEVERE, "Invalid Requirement "+fullPath+" in "+file.getName());
					continue;
				}
				
				//TODO DENY MESSAGE TO BE AN EVENT TYPE????
				List<Executable> denial = plugin.getExecutableManager().getExecutables(file, fullPath+".Deny");
				
				requirement.setName(reqName);
				requirement.setComparison(compareWith);					
				requirement.setValue(value);
				requirement.setDenails(denial);				
				
				reqs.add(requirement);
			}			
		}
		return reqs;
	}
	
	public boolean instanceOfItemRequirement(Requirement req)
	{
		return (req instanceof ItemLoreEquals || req instanceof ItemLoreEqualsIgnoreCase || req instanceof ItemNameEquals || req instanceof ItemNameEqualsIgnoreCase || req instanceof ItemNameContains||req instanceof ItemNameContainsIgnoreCase);		
	}
	
	public boolean passesResult(Player player, ItemStack item, Requirement req)	{
		return req.passesRequirement(player, item);
	}

	public boolean passesRequirements(Player player, ItemStack item, List<Requirement> reqs)
	{				
		for(Requirement req : reqs)
		{
			if(!req.passesRequirement(player, item))
				return false;
		}
		return true;
	}
	
	public Requirement getFromIdentifier(String name)
	{
		if(name==null) return null;
		Requirement req = registered.get(name.toLowerCase());
		
		if(req!=null)
			try {
				return req.getClass().newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	public void register(Requirement req) {
		registered.putIfAbsent(req.getIdentifier().toLowerCase(), req);
	}

	public void startCooldownTimer(List<Requirement> reqs, Player player)
	{
		reqs.stream().filter(req -> req instanceof Cooldown).forEach(req -> ((Cooldown)req).startCooldown(player.getUniqueId()));		
	}

	public boolean passesRequirements(Player player, ItemStack item, boolean physical, List<Requirement> requirements)
	{
		for(Requirement req : requirements)
		{
			if(req instanceof StriclyPhysical)
				 if(!((StriclyPhysical)req).passesRequirement(player, physical))
					 return false;
			
			if(!req.passesRequirement(player, item))
				return false;
		}

		return true;
	}


}
