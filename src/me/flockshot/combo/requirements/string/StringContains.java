package me.flockshot.combo.requirements.string;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.flockshot.combo.requirement.MainRequirement;
import me.flockshot.combo.utils.PlaceholderTranslator;

public class StringContains extends MainRequirement {

	@Override
	public String getIdentifier() {
		return "string contains";
	}

	@Override
	public boolean passesRequirement(Player player, ItemStack item)
	{
		PlaceholderTranslator pt = new PlaceholderTranslator();
		String val = pt.getTranslatedString(player, (String) getValue());
		String comp = pt.getTranslatedString(player, (String) getCompareWith());
		
		if(val.contains(comp))
		    return true;
		else
		{
			runDeniables(player);
			return false;
		}
	}

}
