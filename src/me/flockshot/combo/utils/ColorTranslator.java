package me.flockshot.combo.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

public class ColorTranslator
{	
	public List<String> getTranslatedLore(List<String> lore)
	{
		List<String> translatedList = new ArrayList<String>();
		
		if(lore!=null && !lore.isEmpty())	
			for(String line : lore)
				translatedList.add(getTranslatedString(line));

		return translatedList;
	}
	
	public String getTranslatedString(String st)
	{
		return ChatColor.translateAlternateColorCodes('&', st);
	}
}
