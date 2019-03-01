package me.flockshot.combo.listener;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

	@SuppressWarnings({ "deprecation", "unused" })
	private ItemStack generateItem(String name, List<String> lore, String itemid, int data)
	{
		// TODO Auto-generated method stub
		ItemStack item;
		
		//if(Bukkit.getVersion().contains("1.8"))
		//{
			//if(isNum(itemid)) item = new ItemStack(Integer.parseInt(itemid), 1, (byte) data);
			//else return null;
		//}
		//else
		//{
			item = Bukkit.getVersion().contains("1.13") ?  new ItemStack(Material.getMaterial(itemid)) : isNum(itemid) ? item = new ItemStack(Integer.parseInt(itemid), 1, (byte) data) : new ItemStack(Material.getMaterial(itemid), 1, (byte) data);
		//}
		
		ItemMeta meta = item.getItemMeta();
		meta.setLore(getTranslatedLore(lore));
		meta.setDisplayName(getTranslatedString(name));		
		item.setItemMeta(meta);
		
		return item;
	}
	
	private List<String> getTranslatedLore(List<String> lore)
	{
		List<String> clore = new ArrayList<String>();
		
		if(lore!=null && !lore.isEmpty())
		{			
			Iterator<?> it = lore.iterator();
			String cur;
			
			while(it.hasNext())
			{
				cur = getTranslatedString((String) it.next());
				clore.add(cur);
			}
		}
		return clore;		
	}
	
	private String getTranslatedString(String st)
	{
		return ChatColor.translateAlternateColorCodes('&', st);
	}
	
	private boolean isNum(String str)
	{
	  NumberFormat formatter = NumberFormat.getInstance();
	  ParsePosition pos = new ParsePosition(0);
	  formatter.parse(str, pos);
	  return str.length() == pos.getIndex();
	}
	
	
	
}
