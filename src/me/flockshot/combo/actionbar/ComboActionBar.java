package me.flockshot.combo.actionbar;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import me.flockshot.combo.ComboPlugin;
import me.flockshot.combo.combo.Combo;
import me.flockshot.combo.utils.ColorTranslator;

public class ComboActionBar
{

	private ActionBar actionBar;
	private ComboPlugin plugin;
	private HashMap<UUID, Integer> actionTimerID = new HashMap<UUID, Integer>();
	
    private String formattedR;
    private String formattedL;
	
	public ComboActionBar(ComboPlugin main, ActionBar actionBar)
	{
		this.actionBar = actionBar;
		
		plugin = main;
		
	    formattedR = plugin.getConfig().getString("actionRight");
	    formattedL = plugin.getConfig().getString("actionLeft");
	}
	
	public void preActionBar(Player player, String actionNum, Combo combo)
	{
		UUID uuid = player.getUniqueId();
		FileConfiguration plrConfig = plugin.getPlayerConfig(uuid);
		
		if(plrConfig.contains("ActionBarToggle"))
		{
			if(plrConfig.getBoolean("ActionBarToggle"))
			{
			    String formattedActionFirst = "";
			    String formattedActionSecond = "";
			    String formattedAction = "";
			    
			    int duration = 0;
			    String text = "";
			    
			    if(combo.getFirstAction().equals("left ")) formattedActionFirst = formattedL;
			    else formattedActionFirst = formattedR;
			    
			    if(combo.getSecondAction().equals("right ")) formattedActionSecond = formattedR;
			    else formattedActionSecond = formattedL;
			    
			    if(combo.getThirdAction().equals("right")) formattedAction = formattedR;
			    else formattedAction = formattedL;

			    
			    if(actionNum.equals("first"))
			    {
			    	duration = plugin.getConfig().getInt("actionBardurationFirst");
					text = plugin.getConfig().getString("actionBarFormatFirst");			
			    	text = text.replaceAll("%firstaction%", formattedActionFirst);
			    }
			    else if(actionNum.equals("second"))
			    {
			    	duration = plugin.getConfig().getInt("actionBardurationSecond");
					text = plugin.getConfig().getString("actionBarFormatSecond");			
			    	text = text.replaceAll("%firstaction%", formattedActionFirst).replaceAll("%secondaction%", formattedActionSecond);
			    }
			    else if(actionNum.equals("third"))
			    {
			    	duration = plugin.getConfig().getInt("actionBardurationThird");
					text = plugin.getConfig().getString("actionBarFormatThird");			
			    	text = text.replaceAll("%firstaction%", formattedActionFirst).replaceAll("%secondaction%", formattedActionSecond).replaceAll("%thirdaction%", formattedAction);
			    }			    
			    
			    if(!actionNum.equals("first"))
			    {
			    	if(actionTimerID.containsKey(uuid))
			    	{
			    		Bukkit.getServer().getScheduler().cancelTask(actionTimerID.get(uuid));
			    		actionTimerID.remove(uuid);
			    	}
			    	actionBar.sendActionBar(player, "       ");
			    }
			    
			    sendActionBar(player, new ColorTranslator().getTranslatedString(text), duration);
			}
		}
	}
	  
	public void sendActionBar(final Player player, final String text, int duration)
	{
		actionBar.sendActionBar(player, text);
	    final UUID uuid = player.getUniqueId();
	    
	    if(duration < 60)
	    {
	        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	        int id = scheduler.scheduleSyncDelayedTask(plugin, new Runnable()
	        {
	        	public void run()
	        	{
	        		actionTimerID.remove(uuid);
	        		actionBar.sendActionBar(player, "  ");
	        	}
	        }, duration);
	        actionTimerID.put(uuid, id);
	    }
	    
	    while(duration > 60)
	    {
	    	duration -= 60;
	    	int dur = duration % 60;
	      
	    	BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
	    	scheduler.scheduleSyncDelayedTask(plugin, new Runnable()
	    	{

	    		public void run()
	    		{
	    			actionBar.sendActionBar(player, text);
	    		}
	    	}, dur);
	    }
	}
}
