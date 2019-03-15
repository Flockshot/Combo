package me.flockshot.combo.executables.player;

import org.bukkit.entity.Player;

import me.flockshot.combo.executable.UnknownExecutable;
import me.flockshot.combo.utils.PlaceholderTranslator;

public class PlayerCommand extends UnknownExecutable
{

	@Override
	public String getIdentifier() {
		return "PlayerCommand";
	}

	
    @Override
    public void execute()
    {
        Player player = getPlayerToExecute();
        PlaceholderTranslator translater = new PlaceholderTranslator();
        player.performCommand(""+translater.getTranslatedString(player, getValue()));        
    }
	
}
