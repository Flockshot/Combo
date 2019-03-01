package me.flockshot.combo;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.flockshot.combo.action.ActionManager;
import me.flockshot.combo.actionbar.ActionBar;
import me.flockshot.combo.actionbar.ActionBarv1_10_R1;
import me.flockshot.combo.actionbar.ActionBarv1_11_R1;
import me.flockshot.combo.actionbar.ActionBarv1_12_R1;
import me.flockshot.combo.actionbar.ActionBarv1_13_R1;
import me.flockshot.combo.actionbar.ActionBarv1_13_R2;
import me.flockshot.combo.actionbar.ActionBarv1_8_R1;
import me.flockshot.combo.actionbar.ActionBarv1_8_R2;
import me.flockshot.combo.actionbar.ActionBarv1_8_R3;
import me.flockshot.combo.actionbar.ActionBarv1_9_R1;
import me.flockshot.combo.actionbar.ActionBarv1_9_R2;
import me.flockshot.combo.actionbar.ComboActionBar;
import me.flockshot.combo.combo.ComboManager;
import me.flockshot.combo.executable.ExecutableManager;
import me.flockshot.combo.executables.player.ConsoleCommand;
import me.flockshot.combo.executables.player.PlayerCommand;
import me.flockshot.combo.executables.player.PlayerMessage;
import me.flockshot.combo.executables.world.PlaySound;
import me.flockshot.combo.listener.ComboEvent;
import me.flockshot.combo.listener.InteractEntityEvent;
import me.flockshot.combo.listener.InteractEvent;
import me.flockshot.combo.listener.InteractEvent1_8;
import me.flockshot.combo.listener.JoinEvent;
import me.flockshot.combo.listener.QuitEvent;
import me.flockshot.combo.requirement.RequirementManager;
import me.flockshot.combo.requirements.item.HasEnchant;
import me.flockshot.combo.requirements.item.ItemLoreEquals;
import me.flockshot.combo.requirements.item.ItemLoreEqualsIgnoreCase;
import me.flockshot.combo.requirements.item.ItemNameContains;
import me.flockshot.combo.requirements.item.ItemNameContainsIgnoreCase;
import me.flockshot.combo.requirements.item.ItemNameEquals;
import me.flockshot.combo.requirements.item.ItemNameEqualsIgnoreCase;
import me.flockshot.combo.requirements.number.Equal;
import me.flockshot.combo.requirements.number.Greater;
import me.flockshot.combo.requirements.number.GreaterEqual;
import me.flockshot.combo.requirements.number.Lesser;
import me.flockshot.combo.requirements.number.LesserEqual;
import me.flockshot.combo.requirements.number.NotEqual;
import me.flockshot.combo.requirements.player.Cooldown;
import me.flockshot.combo.requirements.player.HasPermission;
import me.flockshot.combo.requirements.player.JavaScript;
import me.flockshot.combo.requirements.string.StringContains;
import me.flockshot.combo.requirements.string.StringContainsIgnoreCase;
import me.flockshot.combo.requirements.string.StringEquals;
import me.flockshot.combo.requirements.string.StringEqualsIgnoreCase;
import me.flockshot.combo.subactionmanager.SubActionManager;



public class ComboPlugin extends JavaPlugin
{

	//public HashMap<String, String> Combos = new HashMap<String, String>();
	//public HashMap<UUID, String> startedcombo = new HashMap<UUID, String>();
	public ComboActionBar comboActionBar;
	
	private ComboManager comboManage;
	//private TimerManager timerManage;
	private ActionManager actionManage;
	private SubActionManager subActionManage;
	private RequirementManager reqManage;
	private ExecutableManager executableManage;
	
	
	
	
	public static void main(String[] args)
	{
	    // Prints "Hello, World" to the terminal window.
		//Executable something = getFromType("[PlayerCommand]");
		//System.out.println("Hello, World   " + something.getClass().getName());
		
		List<String> first = new ArrayList<String>();
		first.add("First");
		first.add("Second");
		first.add("Third+");
		
		List<String> second = new ArrayList<String>();
		second.add("fIrSt");
		second.add("second");
		second.add("THIR");
		
		
		
		List<String> third = new ArrayList<String>();
		third.add("First");		
		third.add("Third+");
		third.add("Second");
		
		
		System.out.println(first.toString().toLowerCase().contains(second.toString().toLowerCase()));
		
		System.out.println(first.toString().toLowerCase());
		System.out.println(second.toString().toLowerCase());
		//System.out.print(first.equals(third));
		
	}

	public void onEnable()
	{		
		
		File ActionBarToggledir = new File(this.getDataFolder()+ File.separator+"Players");		
		ActionBarToggledir.mkdir();
		
		
		getConfig().options().copyDefaults(true);
		saveConfig();		
		saveConfig();
		
		
		registerEvents();
		registerActionbar();
		
		//setTimerManager(new TimerManager(this));
		
		setComboManager(new ComboManager(this));
		setActionManager(new ActionManager(this));		
		setSubActionManager(new SubActionManager(this));
		setRequirementManager(new RequirementManager(this));
		setExecutableManager(new ExecutableManager(this));
				
		registerAllExecutables();
		registerAllRequirements();

		getActionManager().registerActions(new File(this.getDataFolder()+ File.separator+"Actions"));
    }
	
	
		
	private void registerAllExecutables()
	{
		getExecutableManager().register(new ConsoleCommand());
		
		getExecutableManager().register(new PlayerCommand());
		getExecutableManager().register(new PlayerMessage());
		
		getExecutableManager().register(new PlaySound());
	}
	
	private void registerAllRequirements()
	{
		getRequirementManager().register(new HasEnchant());
		
		getRequirementManager().register(new ItemLoreEquals());
		getRequirementManager().register(new ItemLoreEqualsIgnoreCase());
		getRequirementManager().register(new ItemNameEquals());
		getRequirementManager().register(new ItemNameEqualsIgnoreCase());
		getRequirementManager().register(new ItemNameContains());
		getRequirementManager().register(new ItemNameContainsIgnoreCase());
		
		
		getRequirementManager().register(new Equal());
		getRequirementManager().register(new Greater());
		getRequirementManager().register(new Lesser());
		getRequirementManager().register(new GreaterEqual());
		getRequirementManager().register(new LesserEqual());
		getRequirementManager().register(new NotEqual());
		
		
		getRequirementManager().register(new Cooldown());
		getRequirementManager().register(new HasPermission());
		getRequirementManager().register(new JavaScript());
		
		
		getRequirementManager().register(new StringEquals());
		getRequirementManager().register(new StringEqualsIgnoreCase());
		getRequirementManager().register(new StringContains());
		getRequirementManager().register(new StringContainsIgnoreCase());
	}

	public void onDisable()
	{	
		//TODO SAVE
		Collection<? extends Player> plrs = Bukkit.getServer().getOnlinePlayers();
		Iterator<? extends Player> it = plrs.iterator();
		
		while(it.hasNext())
		{
			getComboManager().removeComboinProgress(it.next().getUniqueId());
		}
		
	}
	
	private void registerEvents()
	{
		// TODO Auto-generated method stub
		getServer().getPluginManager().registerEvents(new ComboEvent(this), this);
		getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
		getServer().getPluginManager().registerEvents(new QuitEvent(this), this);
		getServer().getPluginManager().registerEvents(new InteractEntityEvent(this), this);
		if(Bukkit.getVersion().contains("1.8"))
		{
			getServer().getPluginManager().registerEvents(new InteractEvent1_8(this), this);
		}
		else
		{
			getServer().getPluginManager().registerEvents(new InteractEvent(this), this);
		}
		
	}
	
	
	public ActionManager getActionManager()
	{
		return actionManage;
	}
	public ComboManager getComboManager()
	{
		return comboManage;
	}
	public ComboActionBar getComboActionBar()
	{
	    return comboActionBar;
	}
	
	private void setActionManager(ActionManager actionManage)
	{
		this.actionManage = actionManage;
	}
	
	private void setComboManager(ComboManager comboManage)
	{
		this.comboManage = comboManage;
	}
	
	private void registerActionbar()
	{
		String ver = Bukkit.getVersion();
		ActionBar actionbar;
		if(ver.contains("1.8.4") || ver.contains("1.8.5") || ver.contains("1.8.6") || ver.contains("1.8.7") || ver.contains("1.8.8") || ver.contains("1.8.9"))
		{
			actionbar = new ActionBarv1_8_R3();
		}
		else if(ver.contains("1.8.3"))
		{
			actionbar = new ActionBarv1_8_R2();
		}
		else if(ver.contains("1.8"))
		{
			actionbar = new ActionBarv1_8_R1();
		}
		else if(ver.contains("1.9.4"))
		{
			actionbar = new ActionBarv1_9_R2();
		}
		else if(ver.contains("1.9"))
		{
			actionbar = new ActionBarv1_9_R1();
		}		
		else if(ver.contains("1.10"))
		{
			actionbar = new ActionBarv1_10_R1();
		}
		else if(ver.contains("1.11"))
		{
			actionbar = new ActionBarv1_11_R1();
		}
		else if(ver.contains("1.12")) 
		{
			actionbar = new ActionBarv1_12_R1();
		}
		else if(ver.contains("1.13.1") || ver.contains("1.13.2"))
		{
			actionbar = new ActionBarv1_13_R2();
		}
		else if(ver.contains("1.13"))
		{
			actionbar = new ActionBarv1_13_R1();
		}
		else
		{
			actionbar = null;
		}
		comboActionBar = new ComboActionBar(this, actionbar);
		
	}



	public RequirementManager getRequirementManager() {
		return reqManage;
	}



	public void setRequirementManager(RequirementManager reqManage) {
		this.reqManage = reqManage;
	}



	public SubActionManager getSubActionManager() {
		return subActionManage;
	}



	public void setSubActionManager(SubActionManager subActionManage) {
		this.subActionManage = subActionManage;
	}



	public ExecutableManager getExecutableManager() {
		return executableManage;
	}



	public void setExecutableManager(ExecutableManager manage) {
		this.executableManage = manage;
	}

	public FileConfiguration getPlayerConfig(UUID uuid)
	{
		File plrfile = new File(getDataFolder() + File.separator + "Players", uuid + ".yml");
		
		return YamlConfiguration.loadConfiguration(plrfile);
	}
	/*
	public boolean onCommand(CommandSender sender, Command command, String cmdvalue, String[] args)
	{
		String cmd = cmdvalue.toLowerCase();
		
		try {
			Commands.ProcessCommand(sender, command, cmd, args);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	*/

	

}

