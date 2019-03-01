package me.flockshot.combo.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

import me.flockshot.combo.ComboPlugin;

public class InteractEntityEvent implements Listener
{
	private ComboPlugin plugin;
	
	public InteractEntityEvent(ComboPlugin plug)
	{
		plugin = plug;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onAction(EntityDamageByEntityEvent event)
	{
		if(event.getDamager() instanceof Player)
		{
			Player player = (Player) event.getDamager();
			
			if(player.hasPermission("combo.start"))
			{				
				if((event.getCause().equals(DamageCause.ENTITY_ATTACK) || event.getCause().equals(DamageCause.ENTITY_SWEEP_ATTACK)) && plugin.getConfig().getBoolean("allowPhysical"))
				{
					ItemStack item = Bukkit.getVersion().contains("1.8") ?  player.getInventory().getItemInHand() : player.getInventory().getItemInMainHand();
					plugin.getComboManager().registerCombo(player, "pleft", item, player.isSneaking());					
				}
			}
		}
	}
}
