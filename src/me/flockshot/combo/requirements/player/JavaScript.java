package me.flockshot.combo.requirements.player;

import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.flockshot.combo.executable.Executable;
import me.flockshot.combo.executable.PlayerExecutable;
import me.flockshot.combo.requirement.Requirement;
import me.flockshot.combo.utils.PlaceholderTranslator;

public class JavaScript implements Requirement {

	String name;
	String script;
	List<Executable> executables; 
	
	@Override
	public String getIdentifier() {
		return "javascript";
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
	public String getValue() {
		return script;
	}

	@Override
	public void setValue(Object value) {
		script = (String) value;
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
		PlaceholderTranslator pt = new PlaceholderTranslator();
		String script = pt.getTranslatedString(player, getValue());
		
		ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        
		try
		{
			if((boolean) engine.eval(script)) return true;
			else
			{
				getDenial().stream().filter(exe -> exe instanceof PlayerExecutable).forEach(exe -> ((PlayerExecutable)exe).execute(player));
				return false;
			}
		} catch (ScriptException e) {
			e.printStackTrace();
		}

		getDenial().stream().filter(exe -> exe instanceof PlayerExecutable).forEach(exe -> ((PlayerExecutable)exe).execute(player));
		return false;
		
	}



}
