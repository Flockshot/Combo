package me.flockshot.combo.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;

public class PlaceholderTranslator
{
	private boolean isUsingAPI = false;
	public PlaceholderTranslator()
	{
		if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
		    isUsingAPI = true;			
	}
	
	public String getTranslatedString(Player player, String string)
	{
		if(isUsingAPI)
		    //TODO ADD %cooldown_left% replacement
			return PlaceholderAPI.setPlaceholders(player, string);
		else
		    return string;
	}
}
