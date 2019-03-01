package me.flockshot.combo.executables.player;

import org.bukkit.entity.Player;

import me.flockshot.combo.executable.PlayerExecutable;
import me.flockshot.combo.utils.PlaceholderTranslator;

public class PlayerCommand implements PlayerExecutable
{
	//private SomethingPlayerType type;
	private String value;

	
	public PlayerCommand()
	{
		//setType(type);
		//setValue(value);
	}

	
	@Override
	public String getValue() {
		return value;
	}
	
	@Override
	public void setValue(String value) {
		this.value = value;
	}



	@Override
	public void execute(Player player) {
		
		// TODO Auto-generated method stub
		PlaceholderTranslator translater = new PlaceholderTranslator();
		player.performCommand(""+translater.getTranslatedString(player, getValue()));
		
	}


	@Override
	public String getIdentifier() {
		// TODO Auto-generated method stub
		return "PlayerCommand";
	}


	@Override
	public boolean passesValidity(String value) {
		// TODO Auto-generated method stub
		return true;
	}


	
}
