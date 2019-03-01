package me.flockshot.combo.requirement;

import java.util.HashMap;
import java.util.UUID;

import me.flockshot.combo.utils.NumberUtility;

public class CooldownManager
{
	private HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();
	private long cooldownInMs;
	
	public CooldownManager(String value)
	{
		cooldownInMs = getCooldownInSecs(value)*1000;
	}

	public long getCooldown(UUID uuid)
	{
		if(containsCooldown(uuid))
		{
			return cooldown.get(uuid);
		}
		else return 0;
	
	
	}
	
	public boolean containsCooldown(UUID uuid)
	{
		return cooldown.containsKey(uuid);
	}
	
	public void putCooldown(UUID uuid)
	{
		cooldown.put(uuid, System.currentTimeMillis());
	}
	
	public boolean cooldownPassed(UUID uuid)
	{
		if(containsCooldown(uuid))
		{
			long difference = System.currentTimeMillis()-getCooldown(uuid);
			if(difference<cooldownInMs)
			{			
				return false;
			}
			else return true;
		}
		else return true;
	}
	
	
	
	
	private int getCooldownInSecs(String cooldown)
	{
		char[] cooldownArray = cooldown.toCharArray();
		String current = "";
		String time = "";
		int secs = 0;
		NumberUtility util = new NumberUtility();
		
		for(int i=0; i<cooldownArray.length; i++)
		{
			current = cooldownArray[i]+"";
			if(util.isNum(current))
			{
				time+=current;
			}
			else
			{
				if(current.equalsIgnoreCase("h"))
				{
					secs = secs + (3600*(Integer.parseInt(time)));
					time = "";
				}
				else if(current.equalsIgnoreCase("m"))
				{
					secs = secs + (60*(Integer.parseInt(time)));
					time = "";
				}
				else
				{
					secs = secs + (Integer.parseInt(time));
					time = "";
				}
				//TODO Exception
			}
		}
		
		if(!time.isEmpty() && util.isNum(time))
		{
			secs = secs + Integer.parseInt(time);
			time = "";
		}		
		return secs;
	}
	
}
