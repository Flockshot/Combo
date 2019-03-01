package me.flockshot.combo.listener;

import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.flockshot.combo.ComboPlugin;

public class QuitEvent implements Listener
{
	private ComboPlugin plugin;
	
	public QuitEvent(ComboPlugin plug)
	{
		plugin = plug;
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event)
	{
		UUID uuid = event.getPlayer().getUniqueId();
		plugin.getComboManager().removeComboinProgress(uuid);
		plugin.getComboManager().removeTimer(uuid);
	}
}
