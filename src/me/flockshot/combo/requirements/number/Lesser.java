package me.flockshot.combo.requirements.number;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.flockshot.combo.executable.Executable;
import me.flockshot.combo.executable.PlayerExecutable;
import me.flockshot.combo.requirement.Requirement;
import me.flockshot.combo.utils.NumberUtility;
import me.flockshot.combo.utils.PlaceholderTranslator;

public class Lesser implements Requirement {

	String name;
	String value;
	String compareWith;
	List<Executable> executables; 
	
	@Override
	public String getIdentifier() {
		return "<";
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
		if(value instanceof String) this.value = (String) value;
		else if(value instanceof Integer) this.value = ((Integer) value)+"";
		else this.value = "";		
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
		NumberUtility nUtil = new NumberUtility();
		
		
		String value = pt.getTranslatedString(player, getValue());
		String compare = pt.getTranslatedString(player, getCompareWith());
		
		if(nUtil.isNum(value) && nUtil.isNum(compare))
		{
			double val = Double.valueOf(value);
			double comp = Double.valueOf(compare);
			
			if(val<comp) return true;
			else
			{
				getDenial().stream().filter(exe -> exe instanceof PlayerExecutable).forEach(exe -> ((PlayerExecutable)exe).execute(player));
				return false;
			}
		}
		else
		{
			getDenial().stream().filter(exe -> exe instanceof PlayerExecutable).forEach(exe -> ((PlayerExecutable)exe).execute(player));
			return false;
		}
	}

}
