package me.flockshot.combo.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.flockshot.combo.ComboPlugin;

public class InteractEvent1_8 implements Listener
{
	private ComboPlugin plugin;
	
	public InteractEvent1_8(ComboPlugin plug)
	{
		plugin = plug;
	}
	
	@EventHandler
	public void onAction(PlayerInteractEvent event)
	{
		
		Player player = event.getPlayer();		
		player.sendMessage(event.getAction()+"");
		if(player.hasPermission("combo.start"))
		{
			String action = "";
			
			if(event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK) || event.getAction().equals(Action.PHYSICAL))
			{
				if(!plugin.getConfig().getBoolean("allowPhysical") && event.getAction().equals(Action.PHYSICAL))
				{
					return;
				}
				action = "left";				
			}
			else
			{
				action = "right";
			}
			@SuppressWarnings("deprecation")
			ItemStack item = player.getInventory().getItemInHand();
			
			plugin.getComboManager().registerCombo(player, action, item, player.isSneaking());
		}
	}
}
