package me.flockshot.combo.subactionmanager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import me.flockshot.combo.ComboPlugin;
import me.flockshot.combo.combo.ComboType;
import me.flockshot.combo.executable.Executable;
import me.flockshot.combo.requirement.Requirement;

public class SubActionManager
{
	
	ComboPlugin plugin;
	public SubActionManager(ComboPlugin plugin)
	{
		this.plugin = plugin;
	}
	
	
	public List<SubAction> getSubActions(FileConfiguration file, String path)
	{
		List<SubAction> subActions = new ArrayList<SubAction>();
		
		if(file.contains(path))
		{
		    String fullPath;
            String subActionType;
            for(final String key : file.getConfigurationSection(path).getKeys(false))
            {
                subActionType = key;
                fullPath = path+"."+subActionType;                  
                
                ComboType type = ComboType.valueOf(key);
                
                List<Requirement> reqs = plugin.getRequirementManager().getRequirements(file, fullPath+".Requirements");
                List<Executable> acceptance = plugin.getExecutableManager().getExecutables(file, fullPath+".Executables");
                
                subActions.add(new SubAction(type, reqs, acceptance));                  
            }						
		}
		return subActions;		
	}
	
	public boolean areSubActionsRight(List<SubAction> subs)
	{
	    return subs.stream().filter(sub -> sub.getComboType().toString().startsWith("SR") || sub.getComboType().toString().startsWith("R")).count()>0;
	}
}
