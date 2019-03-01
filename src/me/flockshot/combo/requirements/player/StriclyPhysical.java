package me.flockshot.combo.requirements.player;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.flockshot.combo.executable.Executable;
import me.flockshot.combo.executable.PlayerExecutable;
import me.flockshot.combo.requirement.Requirement;

public class StriclyPhysical implements Requirement {

	String name;
	boolean striclyPhysical;
	List<Executable> executables; 
	
	@Override
	public String getIdentifier() {
		return "stricly physical";
	}

	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Boolean getValue() {
		return striclyPhysical;
	}
	@Override
	public void setValue(Object value) {
		striclyPhysical = (Boolean) value;
	}

	@Override
	public String getCompareWith() {
		return null;
	}
	@Override
	public void setComparison(Object compareWith) {		
	}

	@Override
	public List<Executable> getDenial() {
		return executables;
	}
	@Override
	public void setDenails(List<Executable> executables) {
		this.executables = executables;
	}

	@Override
	public boolean passesRequirement(Player player, ItemStack item)
	{
		return passesRequirement(player, false);		
	}

	public boolean passesRequirement(Player player, boolean physical)
	{
		if(getValue()==physical)
		    return true;
		else
		{
			getDenial().stream().filter(exe -> exe instanceof PlayerExecutable).forEach(exe -> ((PlayerExecutable)exe).execute(player));
			return false;
		}
	}


}
