package me.flockshot.combo.requirement;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.flockshot.combo.executable.Executable;

public interface Requirement
{
	String getIdentifier();
	
	String getName();
	void setName(String name);
	Object getValue();
	void setValue(Object value);
	Object getCompareWith();
	void setComparison(Object compareWith);
	
	List<Executable> getDenial();
	void setDenails(List<Executable> executables);
	
	boolean passesRequirement(Player player, ItemStack item);
	
	//boolean passesValidity(Object value);
}
