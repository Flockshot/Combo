package me.flockshot.combo.executables.player;

import org.bukkit.entity.Player;

import me.flockshot.combo.executable.UnknownExecutable;
import me.flockshot.combo.utils.FullTranslator;

public class PlayerMessage extends UnknownExecutable
{

	@Override
	public void execute()
	{
	    Player player = getPlayerToExecute();
		FullTranslator translator = new FullTranslator();
		player.sendMessage(translator.getTranslatedString(player, getValue()));		
	}

	@Override
	public String getIdentifier() {
		return "PlayerMessage";
	}

}
