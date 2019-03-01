package me.flockshot.combo.combo;

import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.flockshot.combo.ComboPlugin;

public class SoundManager
{
	private boolean playSound  = true;
	private Sound sound = null;
    private float v = 0;
    private float p = 0;
    ComboPlugin plugin;
    
	SoundManager(ComboPlugin plugin)
	{
		this.plugin = plugin;
		
	    //if(Sound.values().toString().toLowerCase().contains(plugin.getConfig().getString("defaultSound").toLowerCase()))
		if(Sound.valueOf(plugin.getConfig().getString("defaultSound"))!=null)
    	{
	    	sound = Sound.valueOf(plugin.getConfig().getString("defaultSound"));
	    	v = plugin.getConfig().getInt("soundVolume");
	    	p = plugin.getConfig().getInt("soundPitch");
    	}
	    else
	    {
    		//TODO DO it with logger
    		plugin.getLogger().log(Level.SEVERE, ChatColor.DARK_RED + "INVALID SOUND IN CONFIG");
    		playSound = false;
	    }
	}
	
	void playSound(Player player)
	{    	
    	FileConfiguration plrConfig = plugin.getPlayerConfig(player.getUniqueId());
    	
		if(plrConfig.contains("SoundToggle"))
		{
			if(plrConfig.getBoolean("SoundToggle") && playSound)
			{				
				player.playSound(player.getLocation(), sound, v, p);
			}
		}
	}
}
