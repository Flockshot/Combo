package me.flockshot.combo.executables.player;

import org.bukkit.entity.Player;

import me.flockshot.combo.executable.PlayerExecutable;
import me.flockshot.combo.utils.PlaceholderTranslator;

public class PlayerCommand implements PlayerExecutable
{
	private String value;
	
	@Override
	public String getValue() {
		return value;
	}	
	@Override
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public void execute(Player player)
	{
		PlaceholderTranslator translater = new PlaceholderTranslator();
		player.performCommand(""+translater.getTranslatedString(player, getValue()));		
	}

	@Override
	public String getIdentifier() {
		return "PlayerCommand";
	}

	@Override
	public boolean passesValidity(String value) {
		return true;
	}
	
}
