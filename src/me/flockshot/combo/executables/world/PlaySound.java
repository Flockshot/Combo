package me.flockshot.combo.executables.world;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import me.flockshot.combo.executable.PlayerExecutable;
import me.flockshot.combo.utils.NumberUtility;
import me.flockshot.combo.utils.PlaceholderTranslator;

public class PlaySound implements PlayerExecutable
{

	private String value = "";

	@Override
	public String getIdentifier() {
		return "PlaySound";
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
	public boolean passesValidity(String value)
	{
	    //SYNTAX: Sound x y z pitch vol
		String[] vals = value.split(" ");
		
		if(vals!=null)
		{
			if(vals.length>=6)
			{
				if(Sound.valueOf(vals[0])!=null)
				{
					NumberUtility nUtil = new NumberUtility();
					if(nUtil.isNum(vals[4]) && nUtil.isNum(vals[5]))
					{
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void execute(Player player)
	{
		PlaceholderTranslator pt = new PlaceholderTranslator();
		
		String[] vals = pt.getTranslatedString(player, value).split(" ");
		NumberUtility nUtil = new NumberUtility();
		
		if(nUtil.isNum(vals[1]) && nUtil.isNum(vals[2]) && nUtil.isNum(vals[3]))
		{
			float x,y,z;
			x = Float.parseFloat(vals[1]);
			y = Float.parseFloat(vals[2]);
			z = Float.parseFloat(vals[3]);
			
			Location loc = new Location(player.getWorld(), x, y, z);
			Sound sound = Sound.valueOf(vals[0]);
			float pitch = Float.parseFloat(vals[4]);
			float amplitude = Float.parseFloat(vals[5]);
			
			player.getWorld().playSound(loc, sound, pitch, amplitude);
		}
	}

}
