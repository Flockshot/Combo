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
		
		//TODO change name
		//System.out.println(ConsoleColors.RED+"EXECUTABLES AT PATH = "+path+ConsoleColors.RESET);
		if(file.contains(path))
		{
			//TODO change name
			for(final String key : file.getStringList(path))
			{
				String name = getIdentifierFromString(key);
				Executable executable = getFromIdentifier(name);
				String value = getValueFromString(key);
				
				//System.out.println(ConsoleColors.RED+key+" with name "+name+" and value "+value);
				
				if(name==null || !executable.passesValidity(value))
				{
					plugin.getLogger().log(Level.SEVERE, "§4Error: Skipping executable line ");
					plugin.getLogger().log(Level.SEVERE, "§4"+key);
					plugin.getLogger().log(Level.SEVERE, "§4in "+file.getName());
					continue;
				}
				
				executable.setValue(value);

				//System.out.println(ConsoleColors.RED+executable.getIdentifier()+" "+executable.getValue() +" IS THE VALUE ");
				executables.add(executable);
			}
		}
		//System.out.println(ConsoleColors.RED+"EXECUTABLES SIZE IS = "+executables.size()+ConsoleColors.RESET);
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
