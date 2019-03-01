package me.flockshot.combo.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerPreComboEvent extends Event
{
	private static final HandlerList handlers = new HandlerList();
	private final Player player;
	private boolean isLeft;
	private boolean isRight;
	private boolean cancelled = false;


	public PlayerPreComboEvent(Player plr, boolean left)
	{
		player = plr;
		if(left)
		{
			setLeft(true);
			setRight(false);
		}
		else
		{
			setLeft(false);
			setRight(true);
		}
	}

	@Override
	public HandlerList getHandlers()
	{
		return handlers;
	}

	public static HandlerList getHandlerList()
	{
		return handlers;
	}
	
	//TODO
	public Player getPlayer()
	{
		return player;
	}


	public boolean isCancelled()
	{
		return cancelled;
	}

	public void setCancelled(boolean cancelled)
	{
		this.cancelled = cancelled;
	}

	public boolean isRight()
	{
		return isRight;
	}

	private void setRight(boolean isRight)
	{
		this.isRight = isRight;
	}

	public boolean isLeft()
	{
		return isLeft;
	}

	private void setLeft(boolean isLeft)
	{
		this.isLeft = isLeft;
	}
	
	
}