package me.flockshot.combo.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class ItemUtility
{	

	public ItemStack generateItem(String itemId, int data)
	{
		if(itemId==null)
		    return null;
		
		NumberUtility nUtil = new NumberUtility();
		ItemStack item;
		
		if(nUtil.isNum(itemId))
			item = generateItem(Integer.parseInt(itemId), data);
		
		else
		{
			if(Bukkit.getVersion().contains("1.8"))
			    return null;
			
			if(Material.getMaterial(itemId)!=null)
				item = generateItem(Material.getMaterial(itemId), data);
			else
			    return null;
		}
		return item;
	}
	/*
	public ItemStack generateItem(String itemId, int data, String skullOwner)
	{		
		return applySkull(generateItem(itemId, data), skullOwner);
	}
	
	/*
	private ItemStack applySkull(ItemStack item, String skullOwner)
	{
		// TODO Auto-generated method stub
		if(item!=null && !skullOwner.isEmpty())
		{
			if(Bukkit.getVersion().contains("1.13"))
			{
				if(item.getType().equals(Material.PLAYER_HEAD))
				{
					return applySkullMeta(item, skullOwner);
				}
			}
			else
			{
				if(item.getType().equals(Material.SKULL_ITEM))
				{
					if(item.getDurability()==3)
					{
						return applySkullMeta(item, skullOwner);
					}
				}
			}
		}
			
		return item;
	}

	private ItemStack applySkullMeta(ItemStack item, String skullOwner)
	{
		if(item.hasItemMeta())
		{
			SkullMeta meta = ((SkullMeta) item.getItemMeta());
			meta.setOwner(skullOwner);
			item.setItemMeta(meta);						
		}
		return item;		
	}
	*/
	@SuppressWarnings("deprecation")
	public ItemStack generateItem(int itemid, int data)
	{
		return Bukkit.getVersion().contains("1.13") ? null : new ItemStack(Material.getMaterial(itemid), 1, (byte) data);
	}
	public ItemStack generateItem(Material mat, int data)
	{
		return Bukkit.getVersion().contains("1.13") ?  new ItemStack(mat) :  new ItemStack(mat, 1, (byte) data);
	}
	
	
	public Map<Enchantment, Integer> convertEnchants(List<String> ench)
	{		
		Map<Enchantment, Integer> enchs = new HashMap<Enchantment, Integer>();
		
		if(ench.isEmpty())
			return enchs;

		for(String current : ench)
		{			
			Enchantment enchant = getEnchant(current);
			int lvl = getEnchantLevel(current);			
			
			enchs.put(enchant, lvl);			
		}		
		return enchs;
	}
	
	@SuppressWarnings("deprecation")
	public Enchantment getEnchant(String ench)
	{
		Enchantment enchant = null;
		
		if(ench.contains(":"))
		{
			NumberUtility util = new NumberUtility();
			String[] split = ench.split(":");	
			enchant = util.isNum(split[0]) ? Enchantment.getById(Integer.parseInt(split[0])) : Enchantment.getByName(split[0]);		
		}
		else
		    Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED+"Invalid enchant format of in file.");
		
		return enchant;
	}
	
	public int getEnchantLevel(String ench)
	{
		int lvl = 0;
		
		if(ench.contains(":"))
		{
			NumberUtility util = new NumberUtility();
			String[] split = ench.split(":");
			lvl = util.isNum(split[1]) ? Integer.parseInt(split[1]) : 0;						
		}
		return lvl;
	}
	
	public boolean compareEnchant(ItemStack item, String ench)
	{		 
		 Enchantment enchant = getEnchant(ench);
		 int lvl = getEnchantLevel(ench);		 
		 
		 if(enchant!=null)
			 if(item.containsEnchantment(enchant))
				 if(item.getEnchantmentLevel(enchant) >= lvl)
					 return true;

		return false;
	}
}
