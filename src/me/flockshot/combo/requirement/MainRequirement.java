package me.flockshot.combo.requirement;

import java.util.List;

import org.bukkit.entity.Player;

import me.flockshot.combo.executable.Executable;
import me.flockshot.combo.executable.PlayerExecutable;

public abstract class MainRequirement implements Requirement {

	String name;
	Object value;
	Object compareWith;
	List<Executable> executables; 
	

	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Object getValue() {
		return value;
	}
	@Override
	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public Object getCompareWith() {
		return compareWith;
	}
	@Override
	public void setComparison(Object compareWith) {
		this.compareWith = compareWith;
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
    public void runDeniables(Player player)
    {
        getDenial().stream().filter(exe -> exe instanceof PlayerExecutable).forEach(exe -> ((PlayerExecutable)exe).execute(player));        
    }
}
