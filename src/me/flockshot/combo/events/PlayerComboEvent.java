package me.flockshot.combo.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.flockshot.combo.combo.Combo;
import me.flockshot.combo.combo.ComboType;

public class PlayerComboEvent extends Event
{
	private static final HandlerList handlers = new HandlerList();
	private final Player player;
	private ComboType type;
	private Combo combo;
	private boolean cancelled = false;

	/*
	public PlayerComboEvent(Player plr, int val) {
		player = plr;
		if(val<=4 && val>=1)
		{
			setComboID(val);	
		}
		else
		{
			setComboID(new Integer(null));
		}
		
		setCombo(getCombination(val));
	}
	*/
	public PlayerComboEvent(Player plr, ComboType type, Combo combo)
	{
		player = plr;
		setComboType(type);
		
		setCombo(combo);
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

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
		

	public Combo getCombo()
	{
		return combo;
	}

	public ComboType getComboType()
	{
		return type;
	}

	public void setComboType(ComboType type)
	{
		this.type = type;
	}

	public void setCombo(Combo combo) {
		this.combo = combo;
	}
	
}