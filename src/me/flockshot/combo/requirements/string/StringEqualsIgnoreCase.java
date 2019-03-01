package me.flockshot.combo.requirements.string;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.flockshot.combo.executable.Executable;
import me.flockshot.combo.executable.PlayerExecutable;
import me.flockshot.combo.requirement.Requirement;
import me.flockshot.combo.utils.PlaceholderTranslator;

public class StringEqualsIgnoreCase implements Requirement {

	String name;
	String value;
	String compareWith;
	List<Executable> executables; 
	
	@Override
	public String getIdentifier() {
		return "string equals ignorecase";
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
	public String getValue() {
		return value;
	}
	@Override
	public void setValue(Object value) {
		this.value = (String) value;
	}

	@Override
	public String getCompareWith() {
		return compareWith;
	}
	@Override
	public void setComparison(Object compareWith) {
		this.compareWith = (String) compareWith;
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
		PlaceholderTranslator pt = new PlaceholderTranslator();
		String val = pt.getTranslatedString(player, getValue());
		String comp = pt.getTranslatedString(player, getCompareWith());
		
		if(val.toLowerCase().equals(comp.toLowerCase()))
		    return true;
		else
		{
			getDenial().stream().filter(exe -> exe instanceof PlayerExecutable).forEach(exe -> ((PlayerExecutable)exe).execute(player));
			return false;
		}
	}
}
