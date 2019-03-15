package me.flockshot.combo.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import me.flockshot.combo.ComboPlugin;

public class InteractEvent implements Listener
{
	private ComboPlugin plugin;
	
	public InteractEvent(ComboPlugin plug)
	{
		plugin = plug;
	}
	
	@EventHandler
	public void onAction(PlayerInteractEvent event)
	{		
		Player player = event.getPlayer();		
		if(event.getHand()!=null)
		{
			if(event.getHand().equals(EquipmentSlot.HAND))
			{
				player.sendMessage(event.getAction()+"");
				if(player.hasPermission("combo.start"))
				{
					String action = "";
					
					if(event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK) || event.getAction().equals(Action.PHYSICAL))
					{
						if(!plugin.getConfig().getBoolean("allowPhysical") && event.getAction().equals(Action.PHYSICAL))
							return;

						action = event.getAction().equals(Action.PHYSICAL) ? "pleft" : "left";						
					}
					else
						action = "right";

					ItemStack item = player.getInventory().getItemInMainHand();
					plugin.getComboManager().registerCombo(player, action, item, player.isSneaking());
				}
			}
		}
	}
}
