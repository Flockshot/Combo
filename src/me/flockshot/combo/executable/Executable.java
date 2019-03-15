package me.flockshot.combo.executable;

import org.bukkit.entity.Player;

public interface Executable
{
	String getIdentifier();
	String getValue();
	void setValue(String value);
	Player getPlayerToExecute();
	void setPlayerToExecute(Player p);
	
	void execute();
	void execute(Player p);
	boolean passesValidity(String value);
	
}
