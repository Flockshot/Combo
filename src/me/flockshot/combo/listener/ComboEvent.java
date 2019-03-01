package me.flockshot.combo.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import me.flockshot.combo.ComboPlugin;
import me.flockshot.combo.combo.ComboType;
import me.flockshot.combo.events.PlayerComboEvent;


public class ComboEvent implements Listener
{
	@SuppressWarnings("unused")
	private ComboPlugin plugin;
	
	public ComboEvent(ComboPlugin plug)
	{
		plugin = plug;
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onCombo(PlayerComboEvent event)
	{
		event.getPlayer().sendMessage("OnComboEvent");
		
		event.getPlayer().sendMessage(event.getCombo()+" 				" +event.getComboType());
		//event.getPlayer().sendMessage(plugin.getActionManager().rf.getConfigurationSection("Right").getKeys(false)+"");
		
		event.getPlayer().sendMessage(ComboType.valueOf("RRL")+"");
		
		//Player player = event.getPlayer();	
		
		//PlayerInventory inv = player.getInventory();	
		/*
		ItemStack[] items = inv.getStorageContents();
		
		
		for(int i=0; i<items.length; i++)
		{
			ItemStack item = items[i];
			
			if(item.getType().equals(Material.EMERALD))
			{
				//TODO 
			}
		}
		
		*/
		//String p = "Right.Heal";
		//FileConfiguration f = plugin.getActionManager().rf;
		//ItemStack item = generateItem(f.getString(p+".Name"), f.getStringList(p+".Lore"), f.getString(p+".Item"), f.getInt(p+".Data"));
		
		//player.sendMessage(inv.getItemInMainHand()+"");
		//player.sendMessage(item+"");
		//player.sendMessage("THE Item matches: It is ---> "+item.equals(inv.getItemInMainHand()) +" "+ (inv.getItemInMainHand().getItemMeta().equals(item.getItemMeta())));
		
	}
	
}
