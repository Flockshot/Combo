package me.flockshot.combo;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.script.ScriptException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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
import me.flockshot.combo.executables.player.potion.PlayePotionEffectAddTime;
import me.flockshot.combo.executables.player.potion.PlayerPotionEffect;
import me.flockshot.combo.executables.player.potion.PlayerPotionEffectOverridable;
import me.flockshot.combo.executables.world.PlaySound;
import me.flockshot.combo.listener.ComboEvent;
import me.flockshot.combo.listener.InteractEntityEvent;
import me.flockshot.combo.listener.InteractEvent;
import me.flockshot.combo.listener.InteractEvent1_8;
import me.flockshot.combo.listener.JoinEvent;
import me.flockshot.combo.listener.QuitEvent;
import me.flockshot.combo.playercache.PlayerCache;
import me.flockshot.combo.playercache.PlayerCacheManager;
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
import me.flockshot.combo.requirements.player.StriclyPhysical;
import me.flockshot.combo.requirements.string.StringContains;
import me.flockshot.combo.requirements.string.StringContainsIgnoreCase;
import me.flockshot.combo.requirements.string.StringEquals;
import me.flockshot.combo.requirements.string.StringEqualsIgnoreCase;
import me.flockshot.combo.subactionmanager.SubActionManager;
import me.flockshot.combo.utils.ColorTranslator;
import me.flockshot.combo.utils.FullTranslator;
import me.flockshot.combo.utils.PlaceholderTranslator;



public class ComboPlugin extends JavaPlugin
{

	//public HashMap<String, String> Combos = new HashMap<String, String>();
	//public HashMap<UUID, String> startedcombo = new HashMap<UUID, String>();
    private static ComboPlugin instance;
    
    public ComboActionBar comboActionBar;
	
    private ComboManager comboManage;
    //private TimerManager timerManage;
    private ActionManager actionManage;
    private SubActionManager subActionManage;
    private RequirementManager reqManage;
    private ExecutableManager executableManage;
    
    private PlayerCacheManager playerCacheManage;
    
    private PlaceholderTranslator pt;
    private ColorTranslator ct;
    private FullTranslator ft;

	
    public static void printPairs(List<Integer> sortedList, int desiredNum)
    {
        Integer[] nums = (Integer[]) sortedList.toArray();
        for(int i=0; i<nums.length-1; i++)
            if(nums[i]+nums[i+1]==desiredNum)
                System.out.println( nums[i]+" , "+nums[i+1]);
    }
    
    public static void main(String[] args) throws ScriptException
    {
        //List<Integer> sortedList = Arrays.asList(2, 3, 4, 5, 6, 7);
        //int desiredNum = 11;
        //printPairs(sortedList, desiredNum);
        
        //ScriptEngineManager mgr = new ScriptEngineManager();
        //ScriptEngine engine = mgr.getEngineByName("JavaScript");
        //String foo = "40+2";
        
        
        
        
        
        int num = 20;
        Integer intNum = 21;
        char[] string = "this is char".toCharArray();
        String stringString = "this is string";
        
        boolean check = true;
        Boolean boolCheck = false;
        PlayerCache cache = new PlayerCache(UUID.randomUUID());
        
        System.out.println("BEFORE");
        System.out.println(num);
        System.out.println(intNum);
        System.out.println(string);
        System.out.println(stringString);
        System.out.println(check);
        System.out.println(boolCheck);
        System.out.println(cache.getUuid());
        
        changeAll(num, intNum, string, stringString, check, boolCheck, cache);
        
        System.out.println("AFTER");
        System.out.println(num);
        System.out.println(intNum);
        System.out.println(string);
        System.out.println(stringString);
        System.out.println(check);
        System.out.println(boolCheck);
        System.out.println(cache.getUuid());
        
        
        //System.out.println(new NumberUtility().isNumMath(foo) + "   "+ engine.eval(foo));
    }
    
    private static void changeAll(int num, Integer intNum, char[] string, String stringString, boolean check,
            Boolean boolCheck, PlayerCache cache)
    {
        num = 30;
        intNum = 31;
        string = "this is char after change".toCharArray();
        stringString = "this is string after changed";
        
        check = false;
        boolCheck = true;
        cache.setUUID(UUID.randomUUID());;
        
    }

    public void mm(List<Integer> sortedList, int desiredNum)
    {
        sortedList.stream().filter(num -> sortedList.indexOf(num)<sortedList.size()-1 ? num+sortedList.get(sortedList.indexOf(num)+1)==desiredNum : false).forEach(num -> System.out.print(num +" , "+sortedList.get(sortedList.indexOf(num)+1)));
    }

    public static ComboPlugin getInstance()
    {
        return instance;
    }
    
    public void onEnable()
    {
        instance = this;
        
        File ActionBarToggledir = new File(this.getDataFolder()+ File.separator+"Players");		
        ActionBarToggledir.mkdir();
        
        //File f = new File("src/x.txt");
        //TO GET File from source folder, to make the first time YML for Actions of Warrior, Archer, Wizard, Tank
        
        initializePlaceholders();
		
        getConfig().options().copyDefaults(true);
        saveConfig();		
        saveConfig();		
		
        registerEvents();
        registerActionbar();
		
        setComboManager(new ComboManager(this));
        setActionManager(new ActionManager(this));		
        setSubActionManager(new SubActionManager(this));
        setRequirementManager(new RequirementManager(this));
        setExecutableManager(new ExecutableManager(this));
            	
        registerAllExecutables();
        registerAllRequirements();
        
        
        
        getActionManager().registerActions(new File(this.getDataFolder()+ File.separator+"Actions"));
        
        setPlayerCacheManager(new PlayerCacheManager());
    }

    private void initializePlaceholders()
    {
        setFt(new FullTranslator());
        setPt(new PlaceholderTranslator());
        setCt(new ColorTranslator());        
    }

    public void onDisable()
    {	
		//TODO SAVE
        Bukkit.getServer().getOnlinePlayers().forEach(plr -> getComboManager().removeComboinProgress(plr.getUniqueId()));		
    }
	
	
    private void registerEvents()
    {
        getServer().getPluginManager().registerEvents(new ComboEvent(this), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
        getServer().getPluginManager().registerEvents(new QuitEvent(this), this);
        getServer().getPluginManager().registerEvents(new InteractEntityEvent(this), this);
		
        if(Bukkit.getVersion().contains("1.8"))
            getServer().getPluginManager().registerEvents(new InteractEvent1_8(this), this);		
        else
            getServer().getPluginManager().registerEvents(new InteractEvent(this), this);
    }
	
    private void registerAllExecutables()
    {
        getExecutableManager().register(new ConsoleCommand());
        
        getExecutableManager().register(new PlayerCommand());
        getExecutableManager().register(new PlayerMessage());
        
        getExecutableManager().register(new PlayerPotionEffect());
        getExecutableManager().register(new PlayePotionEffectAddTime());
        getExecutableManager().register(new PlayerPotionEffectOverridable());

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
        getRequirementManager().register(new StriclyPhysical());
        getRequirementManager().register(new JavaScript());        
        
        getRequirementManager().register(new StringEquals());
        getRequirementManager().register(new StringEqualsIgnoreCase());
        getRequirementManager().register(new StringContains());
        getRequirementManager().register(new StringContainsIgnoreCase());
    }
	    
    public ActionManager getActionManager() {
        return actionManage;
    }
    private void setActionManager(ActionManager actionManage) {
        this.actionManage = actionManage;
    }
	
    public ComboManager getComboManager() {
        return comboManage;
    }
    private void setComboManager(ComboManager comboManage) {
        this.comboManage = comboManage;
    }
	
    public ComboActionBar getComboActionBar() {
        return comboActionBar;
    }
	
    private void registerActionbar()
    {
        String ver = Bukkit.getVersion();
        ActionBar actionbar;
        if(ver.contains("1.8.4") || ver.contains("1.8.5") || ver.contains("1.8.6") || ver.contains("1.8.7") || ver.contains("1.8.8") || ver.contains("1.8.9"))
            actionbar = new ActionBarv1_8_R3();
		else if(ver.contains("1.8.3"))
		    actionbar = new ActionBarv1_8_R2();
		else if(ver.contains("1.8"))
		    actionbar = new ActionBarv1_8_R1();
		else if(ver.contains("1.9.4"))
		    actionbar = new ActionBarv1_9_R2();
		else if(ver.contains("1.9"))
		    actionbar = new ActionBarv1_9_R1();
		else if(ver.contains("1.10"))
		    actionbar = new ActionBarv1_10_R1();
		else if(ver.contains("1.11"))
		    actionbar = new ActionBarv1_11_R1();
		else if(ver.contains("1.12")) 
		    actionbar = new ActionBarv1_12_R1();
		else if(ver.contains("1.13.1") || ver.contains("1.13.2"))
		    actionbar = new ActionBarv1_13_R2();
		else if(ver.contains("1.13"))
		    actionbar = new ActionBarv1_13_R1();
		else
		    actionbar = null;
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

    public PlaceholderTranslator getPt() {
        return pt;
    }

    public void setPt(PlaceholderTranslator pt) {
        this.pt = pt;
    }

    public ColorTranslator getCt() {
        return ct;
    }

    public void setCt(ColorTranslator ct) {
        this.ct = ct;
    }

    public FullTranslator getFt() {
        return ft;
    }

    public void setFt(FullTranslator ft) {
        this.ft = ft;
    }

    public PlayerCacheManager getPlayerCacheManager() {
        return playerCacheManage;
    }

    public void setPlayerCacheManager(PlayerCacheManager playerCache) {
        this.playerCacheManage = playerCache;
    }

	

}

