package me.flockshot.combo.executables.player;

import org.bukkit.entity.Player;

import me.flockshot.combo.executable.PlayerExecutable;
import me.flockshot.combo.utils.FullTranslator;

public class PlayerMessage implements PlayerExecutable {

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
		FullTranslator translator = new FullTranslator();
		player.sendMessage(translator.getTranslatedString(player, getValue()));		
	}

	@Override
	public String getIdentifier() {
		return "PlayerMessage";
	}

	@Override
	public boolean passesValidity(String value) {
		return true;
	}

}
