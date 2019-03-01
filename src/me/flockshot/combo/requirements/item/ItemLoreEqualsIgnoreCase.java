package me.flockshot.combo.requirements.item;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.flockshot.combo.executable.Executable;
import me.flockshot.combo.executable.PlayerExecutable;
import me.flockshot.combo.requirement.Requirement;
import me.flockshot.combo.utils.FullTranslator;

public class ItemLoreEqualsIgnoreCase implements Requirement {
	
	String name;
	List<String> value;
	List<Executable> executables; 
	
	@Override
	public String getIdentifier() {
		return "itemlore equals ignorecase";
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
	public List<String> getValue() {
		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setValue(Object value) {
		this.value = (List<String>) value;
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
		FullTranslator ft = new FullTranslator();
		
		List<String> val = ft.getTranslatedList(player, getValue());

		if(item.hasItemMeta())
			if(item.getItemMeta().hasLore())
			{
				return item.getItemMeta().getLore().toString().toLowerCase().equals(val.toString().toLowerCase());
			}


		getDenial().stream().filter(exe -> exe instanceof PlayerExecutable).forEach(exe -> ((PlayerExecutable)exe).execute(player));
		return false;
			
	}

}
