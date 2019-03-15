package me.flockshot.combo.listener;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import me.flockshot.combo.ComboPlugin;
import me.flockshot.combo.combo.ComboType;

public class JoinEvent implements Listener
{
	private ComboPlugin plugin;
	
	public JoinEvent(ComboPlugin plug)
	{
		plugin = plug;
	}
	
	private int test = 1;
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
		plugin.getPlayerCacheManager().addPlayer(player);
	}
	
	//TODO REMOVE IT
	@EventHandler
    public void onChat(AsyncPlayerChatEvent event)
    {
        event.getPlayer().sendMessage(plugin.getCt().getTranslatedString("&8===[&6Stress Test #" + test + "&8]==="));
        double avg = 0;
	    for(int i =1; i<=10; i++)
	    {
	        long startTime = System.currentTimeMillis();
	        long operations = 0;
	        
	        while (System.currentTimeMillis() - startTime <= 100)
	        {
	            plugin.getActionManager().callAction(ComboType.RRR, event.getPlayer(), event.getPlayer().getInventory().getItemInMainHand(), false);
	            operations++;
	        }
	        avg+= (((double) operations) / 100);
	    }
	    event.getPlayer().sendMessage(plugin.getCt().getTranslatedString("&8===[&6Data Reading Stress Test&8]==="));
	    event.getPlayer().sendMessage(plugin.getCt().getTranslatedString("&aOperations (per millisecond):&6 " + (avg/10)));
	    
    }
	

	
}
