package me.flockshot.combo.executables.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.flockshot.combo.executable.UnknownExecutable;
import me.flockshot.combo.utils.PlaceholderTranslator;

public class ConsoleCommand extends UnknownExecutable {

	@Override
	public String getIdentifier() {
		return "ConsoleCommand";
	}

    @Override
    public void execute()
    {
        Player player = getPlayerToExecute();
        PlaceholderTranslator translater = new PlaceholderTranslator();
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), translater.getTranslatedString(player, getValue()));  
    }

}
