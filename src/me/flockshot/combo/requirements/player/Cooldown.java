package me.flockshot.combo.requirements.player;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.flockshot.combo.executable.Executable;
import me.flockshot.combo.executable.PlayerExecutable;
import me.flockshot.combo.requirement.CooldownManager;
import me.flockshot.combo.requirement.Requirement;

public class Cooldown implements Requirement {
	
	String name;
	CooldownManager cooldown;
	List<Executable> executables;
	
	@Override
	public String getIdentifier() {
		return "cooldown";
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
	public CooldownManager getValue() {
		return cooldown;
	}
	/*
	public CooldownManager getCooldown() {
		return cooldown;
	}
	*/
	@Override
	public void setValue(Object value) {
		cooldown = new CooldownManager((String) value);
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
		if(getValue().cooldownPassed(player.getUniqueId())) return true;
		else
		{
			getDenial().stream().filter(exe -> exe instanceof PlayerExecutable).forEach(exe -> ((PlayerExecutable)exe).execute(player));
			return false;
		}
	}


	

}
