package me.flockshot.combo.executables.player.potion;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.flockshot.combo.executable.UnknownExecutable;
import me.flockshot.combo.utils.NumberUtility;
import me.flockshot.combo.utils.PlaceholderTranslator;

public class PlayerPotionEffect extends UnknownExecutable
{

	private String value = "";
	//private boolean ambient = false;
	//private boolean particles = true;
	//private boolean icon = true;

	@Override
	public String getIdentifier() {
		return "PlayerPotionEffect";
	}
	
    @Override
	public boolean passesValidity(String value)
	{
	    //SYNTAX:  PotionType duration amp ambient particles
		String[] vals = value.split(" ");		
		
		if(vals!=null)		
		    if(vals.length>=3)
                if(getPotionType(vals[0])!=null)                                 
                    return true;
		
		return false;
	}
	
	
	@Override
	public void execute()
	{
	    Player player = getPlayerToExecute();
		PlaceholderTranslator pt = new PlaceholderTranslator();
		
		String[] vals = pt.getTranslatedString(player, value).split(" ");
		NumberUtility nUtil = new NumberUtility();
		
		if(nUtil.isNum(vals[1]) && nUtil.isNum(vals[2]))
		{
			PotionEffectType type = getPotionType(vals[0]);
			int duration = Integer.valueOf(vals[1]);
			int amp = Integer.valueOf(vals[2]);			
			
			boolean ambient = vals.length>=4 ? Boolean.valueOf(vals[3]) : false;
			boolean particles = vals.length>=5 ? Boolean.valueOf(vals[4]) : true;
			//boolean icon = vals.length>=6 ? Boolean.valueOf(vals[5]) : true;
			//PotionEffect potion = new PotionEffect(type, duration, amp, ambient, particles);
			
			player.addPotionEffect(new PotionEffect(type, duration, amp, ambient, particles));
		}
	}
	
	@SuppressWarnings("deprecation")
    protected PotionEffectType getPotionType(String val)
    {
        NumberUtility nUtil = new NumberUtility();
        if(nUtil.isNum(val))
            return PotionEffectType.getById(Integer.parseInt(val));
        else
            return PotionEffectType.getByName(val);
    }

}
