package me.flockshot.combo.executable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.flockshot.combo.ComboPlugin;

public class ExecutableManager 
{
	ComboPlugin plugin;
	HashMap<String, Executable> registered = new HashMap<String, Executable>();
	
	public ExecutableManager(ComboPlugin plugin)
	{
		this.plugin = plugin;
	}
	
	public List<Executable> getExecutables(FileConfiguration file, String path)
	{
		List<Executable> executables = new ArrayList<Executable>();
		
		if(file.contains(path))
		{
			for(final String key : file.getStringList(path))
			{
				String name = getIdentifierFromString(key);
				Executable executable = getFromIdentifier(name);
				String value = getValueFromString(key);
				
				if(name==null || executable ==null)
				{
					plugin.getLogger().log(Level.SEVERE, "§4Error: Skipping executable line ");
					plugin.getLogger().log(Level.SEVERE, "§4"+key);
					plugin.getLogger().log(Level.SEVERE, "§4in "+file.getName());
					continue;
				}
				if(!executable.passesValidity(value))
				{
				    plugin.getLogger().log(Level.SEVERE, "§4Error: Skipping executable, "+key);
				    plugin.getLogger().log(Level.SEVERE, "§4It does not passes validity ");
				    continue;
				}
				
				
				executable.setValue(value);
				executables.add(executable);
			}
		}
		return executables;
	}
	
	
	private String getValueFromString(String val)
	{
		String value = val.substring(val.indexOf("]")+1);
		while(value.startsWith(" "))
		{
			value = value.substring(1);
		}
		return value;
	}
	
	public Executable getFromIdentifier(String name)
	{
		if(name==null) return null;
		Executable exe = registered.get(name.toLowerCase());
		
		if(exe!=null)
			try {
				return exe.getClass().newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	
	public void register(Executable executable)
	{
		registered.putIfAbsent(executable.getIdentifier().toLowerCase(), executable);
	}

	private String getIdentifierFromString(String string)
	{
		String type = StringUtils.substringBetween(string, "[", "]");
		type = type.replaceAll(" ", "");
		return type;
	}
	
	public void executeExecutables(Player player, List<Executable> executables)
	{
		executables.stream().filter(exe -> exe instanceof PlayerExecutable).forEach(exe -> ((PlayerExecutable)exe).execute(player));
	}

}
