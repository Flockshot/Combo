package me.flockshot.combo.listener;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
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
			Player damager = (Player) event.getDamager();

			if(damager.hasPermission("combo.start"))
			{				
				if((event.getCause().equals(DamageCause.ENTITY_ATTACK) || event.getCause().equals(DamageCause.ENTITY_SWEEP_ATTACK)) && plugin.getConfig().getBoolean("allowPhysical"))
				{
				    if(damager.getNearbyEntities(0.3, 0.3, 0.3)!=null)
				    {
				        //TODO: REMOVE IT
				        //player.sendMessage("Entity Heres");
				        
				        List<Entity> ent = damager.getNearbyEntities(0.3, 0.3, 0.3);
				        if(ent.stream().filter(entity -> entity.getType().equals(EntityType.ARROW) || entity.getType().equals(EntityType.ARMOR_STAND) || entity.getType().equals(EntityType.DROPPED_ITEM) || entity.getType().equals(EntityType.DROPPED_ITEM) || entity.equals(damager)).count()>0)
				        {
				            ItemStack item = Bukkit.getVersion().contains("1.8") ?  damager.getInventory().getItemInHand() : damager.getInventory().getItemInMainHand();
		                    plugin.getComboManager().registerCombo(damager, "pleft", item, damager.isSneaking());
				        }
				    }
				        
				    //GROUND CHECK
                    //if(player.getLocation().getBlockY()!=player.getLocation().getY())
				    
                    
				}
			}
			
			if(event.getEntity() instanceof Player)
			{
			    Player victim = (Player) event.getEntity();
			    plugin.getPlayerCacheManager().getPlayerCache(damager).setVictim(victim);
			    plugin.getPlayerCacheManager().getPlayerCache(victim).setAttacker(damager);
			}
			
			
			
		}
	}
	
}
