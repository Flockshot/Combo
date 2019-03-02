package me.flockshot.combo.executables.player;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.flockshot.combo.executable.PlayerExecutable;
import me.flockshot.combo.utils.NumberUtility;
import me.flockshot.combo.utils.PlaceholderTranslator;

public class PlayerPotionEffect implements PlayerExecutable
{

	private String value = "";
	private boolean ambient;
	private boolean particles = true;
	private boolean icon = true;

	@Override
	public String getIdentifier() {
		return "PlayerPotionEffect";
	}

	@Override
	public String getValue() {
		return value;
	}
	@Override
	public void setValue(String value) {
		this.value = value;		
	}
	
	@SuppressWarnings("deprecation")
    @Override
	public boolean passesValidity(String value)
	{
	    //SYNTAX:  PotionType duration amp ambient particles icon
		String[] vals = value.split(" ");
		
		
		if(vals!=null)		
		    if(vals.length>=3)              
                if(getPotionType(vals[0])!=null)                                 
                    return true;
		
		return false;
	}
	
	
	@Override
	public void execute(Player player)
	{
		PlaceholderTranslator pt = new PlaceholderTranslator();
		
		String[] vals = pt.getTranslatedString(player, value).split(" ");
		NumberUtility nUtil = new NumberUtility();
		
		if(nUtil.isNum(vals[1]) && nUtil.isNum(vals[2]))
		{
			float duration, amp;			
			duration = Float.parseFloat(vals[0]);
			amp = Float.parseFloat(vals[1]);
			PotionEffectType type = getPotionType(vals[0]);
			
			
			Sound sound = Sound.valueOf(vals[3]);
			float pitch = Float.parseFloat(vals[4]);
			float amplitude = Float.parseFloat(vals[5]);
			
			player.getWorld().playSound(loc, sound, pitch, amplitude);
		}
	}
	
	private PotionEffectType getPotionType(String val)
    {
        NumberUtility nUtil = new NumberUtility();
        if(nUtil.isNum(val))
            return PotionEffectType.getById(Integer.parseInt(vals[0]));
        else
            return PotionEffectType.getByName(vals[0]);
    }

}
