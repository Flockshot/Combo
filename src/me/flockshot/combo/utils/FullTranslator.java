package me.flockshot.combo.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class FullTranslator
{
	public String getTranslatedString(Player player, String string)
	{
		ColorTranslator ct = new ColorTranslator();
		PlaceholderTranslator pt = new PlaceholderTranslator();
		
		return ct.getTranslatedString(pt.getTranslatedString(player, string));		
	}
	
	public List<String> getTranslatedList(Player player, List<String> list)
	{
		List<String> translatedList = new ArrayList<String>();
		
		if(list!=null && !list.isEmpty())
			for(String line : list)
				translatedList.add(getTranslatedString(player, line));
		
		return translatedList;
	}
}
