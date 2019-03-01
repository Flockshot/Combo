package me.flockshot.combo.executables.player;

import org.bukkit.entity.Player;

import me.flockshot.combo.executable.PlayerExecutable;
import me.flockshot.combo.utils.ColorTranslator;

public class PlayerMessage implements PlayerExecutable {

	private String value;
	public PlayerMessage() {}

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
		ColorTranslator translator = new ColorTranslator();
		player.sendMessage(translator.getTranslatedString(getValue()));
		
	}

	@Override
	public String getIdentifier() {
		// TODO Auto-generated method stub
		return "PlayerMessage";
	}

	@Override
	public boolean passesValidity(String value) {
		// TODO Auto-generated method stub
		return true;
	}

}
