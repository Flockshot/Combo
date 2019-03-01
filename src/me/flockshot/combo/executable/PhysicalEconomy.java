package me.flockshot.combo.executable;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface PhysicalEconomy
{
	void addEconomy(Inventory inv);
	void removeEconomy(Inventory inv);
	int getEconomyAmount(Inventory inv);
	
	void updateEconomy(ItemStack item);
	
	Material getEconomyType();
	void setEconomyType(Material mat);
	
	int getEconomyValue();
	
	PhysicalEconomy getNextInTier();
	ItemStack convertToNextTier();	
}
