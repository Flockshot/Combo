package me.flockshot.combo.utils;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
import me.flockshot.combo.ComboPlugin;
import me.flockshot.combo.action.Action;
import me.flockshot.combo.combo.ComboType;
import me.flockshot.combo.requirement.Requirement;
import me.flockshot.combo.requirements.player.Cooldown;

public class PlaceholderTranslator
{
	private boolean isUsingAPI = false;
	private ComboPlugin plugin;
	
	public PlaceholderTranslator()
	{
	    this.plugin = ComboPlugin.getInstance();
		if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
		    isUsingAPI = true;
	}
	
	public String getTranslatedString(Player player, String string)
	{
		if(isUsingAPI)
			string = PlaceholderAPI.setPlaceholders(player, string);
		
		string = replacePluginPlaceholders(player, string);

        //TODO ADD MATH EVALUATIONS
		return string;//(String) new NumberUtility().applyMath(string);
	}
	
    private String replacePluginPlaceholders(Player player, String string)
	{
	    if(string.contains("%cooldown_left"))
	    {
	        
	        String placeholder = StringUtils.substringBetween(string, "%cooldown_left_", "%");
            String[] stringArr = placeholder.split("_");
            
         
	        if(stringArr.length>=2)
	        {
	            
	            
	            
	            //doPlaceholderThroughStream(stringArr, placeholder);
	            //doPlaceholderThroughFor(stringArr, placeholder);
	            /*
	            List<Action> finalAct = plugin.getActionManager().getActions().stream().filter(act -> act.getName().equals(stringArr[0])).collect(Collectors.toList());
	            if(finalAct.size()>=1)
	            {
	                UUID uuid = player.getUniqueId();
	                if(stringArr[1].toLowerCase().equals("global"))
	                {
	                    finalAct.get(0).getGlobalRequirements().stream().filter(req -> req instanceof Cooldown).forEach(req -> string.replace("%cooldown_left_"+placeholder+"%",  ((Cooldown)req).getCooldown(uuid)));
	                }
	                else
	                {
	                    ComboType type = ComboType.valueOf(stringArr[1]);
	                    if(type!=null)
	                        finalAct.get(0).getSubActionFromType(type).getRequirements().stream().filter(req -> req instanceof Cooldown).forEach(req -> string.replaceAll("%cooldown_left_"+placeholder+"%", ((Cooldown)req).getCooldown(uuid)));
	                    
	                }
	                return string;
	            }
	            */
	            //FOR LOOP
	            
	            for(Action act : plugin.getActionManager().getActions())
	            {
	                if(act.getName().equals(stringArr[0]))
	                {
	                    UUID uuid = player.getUniqueId();
	                    
	                    if(stringArr[1].toLowerCase().equals("global"))
	                    {
	                        for(Requirement req : act.getGlobalRequirements())	                        
	                            if(req instanceof Cooldown)	                            
	                                string = string.replace("%cooldown_left_"+placeholder+"%", ((Cooldown) req).getCooldown(uuid));
	                    }
	                    else
	                    {
	                        ComboType type = ComboType.valueOf(stringArr[1]);
	                        if(type!=null)
	                            for(Requirement req : act.getSubActionFromType(type).getRequirements())
	                                if(req instanceof Cooldown)	                                
	                                    string = string.replace("%cooldown_left_"+placeholder+"%", ((Cooldown) req).getCooldown(uuid));
	                    }
	                    return string;
	                }
	            }
	            
	            
	        }
	    }
	    //return string.replace("%cooldown_left%", );
        return string;
	}


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
