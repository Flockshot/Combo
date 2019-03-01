package me.flockshot.combo.subactionmanager;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.configuration.ConfigurationSection;
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
			final ConfigurationSection pathNames = file.getConfigurationSection(path);
			
			if(pathNames!=null)
			{
				String fullPath;
				String subActionType;
				for(final String key : pathNames.getKeys(false))
				{
					// key will now be either '1' or '2'. Keep in mind, if you were to put true as a param in #getKeys, it would return more than '1' or '2'.
					// For example, it'd also retrun '1.material', '1.slot' etc. You can debug to see yourself.
					
					subActionType = key;
					fullPath = path+"."+subActionType;
					
					
					ComboType type = ComboType.valueOf(key);
					
					List<Requirement> reqs = plugin.getRequirementManager().getRequirements(file, fullPath+".Requirements");
					List<Executable> acceptance = plugin.getExecutableManager().getExecutables(file, fullPath+".Executables");
					
					subActions.add(new SubAction(type, reqs, acceptance));					
				}
			}			
		}
		//TODO
		return subActions;
		
	}
}
