package me.flockshot.combo.executables.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.flockshot.combo.executable.PlayerExecutable;
import me.flockshot.combo.utils.PlaceholderTranslator;

public class ConsoleCommand implements PlayerExecutable {

	private String value;

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public void setValue(String value) {
		// TODO Auto-generated method stub
		this.value = value;
	}

	@Override
	public void execute(Player player) {
		// TODO Auto-generated method stub
		PlaceholderTranslator translater = new PlaceholderTranslator();
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), translater.getTranslatedString(player, getValue()));
		
	}

	@Override
	public String getIdentifier() {
		return "ConsoleCommand";
	}

	@Override
	public boolean passesValidity(String value) {
		// TODO Auto-generated method stub
		return true;
	}


}
