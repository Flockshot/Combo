package me.flockshot.combo.combo;

import me.flockshot.combo.ComboPlugin;

public class ComboTimer
{
	private int waitAfterFirstAction;
	private int waitAfterSecondAction;
	
	private Long startTime = 0l;
	private boolean first = false;
	
	public ComboTimer(ComboPlugin plugin)
	{
	    waitAfterFirstAction = plugin.getConfig().getInt("waitAfterFirstAction");
	    waitAfterSecondAction = plugin.getConfig().getInt("waitAfterSecondAction");
	}
	
	public boolean passesTimers()
	{
		int time = 0;
		
		if(first)
			time = waitAfterFirstAction;
		else
			time = waitAfterSecondAction;			
		
		long difference = System.currentTimeMillis()-startTime;
		if(difference<=time)
			return true;		
		return false;		
	}
	

	public void startFirstTimer()
	{
		startTime = System.currentTimeMillis();
		first = true;
	}
	public void startSecondTimer()
	{
		startTime = System.currentTimeMillis();
		first = false;
	}
	
	public void resetTimer() {
		startTime = 0l;
	}
}
