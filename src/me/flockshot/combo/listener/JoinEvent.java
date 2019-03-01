package me.flockshot.combo.listener;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.flockshot.combo.ComboPlugin;

public class JoinEvent implements Listener
{
	private ComboPlugin plugin;
	
	public JoinEvent(ComboPlugin plug)
	{
		plugin = plug;
	}
	

	@EventHandler
	public void onJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		UUID uuid = player.getUniqueId();
		File playerfile = new File(plugin.getDataFolder() + File.separator + "Players", uuid + ".yml");
		
		if(!playerfile.exists())
		{
			try
			{
				playerfile.createNewFile();
				
				FileConfiguration playerfileconfig = YamlConfiguration.loadConfiguration(playerfile);
				
				playerfileconfig.set("Name", player.getName());
				playerfileconfig.set("ActionBarToggle", plugin.getConfig().get("defaultActionBarToggle"));	
				playerfileconfig.set("SoundToggle", plugin.getConfig().get("defaultSoundToggle"));
				
				playerfileconfig.save(playerfile);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		plugin.getComboManager().addTimer(uuid);
		
	}	
	
}
