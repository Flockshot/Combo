package me.flockshot.combo.executable;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Economy implements PhysicalEconomy
{
	Material mat;
	PhysicalEconomy nextInTier;
	
	Economy(Material mat, PhysicalEconomy next)
	{
		setEconomyType(mat);
		setNextInTier(next);
	}

	public void setEconomyType(Material mat)
	{
		// TODO Auto-generated method stub
		this.mat = mat;
	}

	@Override
	public void addEconomy(Inventory inv) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEconomy(Inventory inv) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getEconomyAmount(Inventory inv) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateEconomy(ItemStack item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Material getEconomyType() {
		// TODO Auto-generated method stub
		return mat;
	}

	@Override
	public int getEconomyValue() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public ItemStack convertToNextTier()
	{
		//TODO
		return null;		
	}
	
	@Override
	public PhysicalEconomy getNextInTier() {
		// TODO Auto-generated method stub
		return nextInTier;
	}

	private void setNextInTier(PhysicalEconomy next)
	{
		// TODO Auto-generated method stub
		nextInTier = next;
	}


	
}
