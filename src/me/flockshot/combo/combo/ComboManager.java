package me.flockshot.combo.combo;

import java.util.HashMap;
import java.util.UUID;
import me.flockshot.combo.ComboPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ComboManager
{
	ComboPlugin plugin;
	private HashMap<UUID, Combo> startedCombo = new HashMap<UUID, Combo>();
	private HashMap<UUID, ComboTimer> playerTimer = new HashMap<UUID, ComboTimer>();
	private SoundManager soundManager;
	
	public ComboManager(ComboPlugin main)
	{
		plugin = main;
		soundManager = new SoundManager(plugin);
	}

	public void registerCombo(Player player, String action, ItemStack item, boolean isShift)
	{
	    UUID uuid = player.getUniqueId();
	    boolean physical = true;
	    
	    if(item != null && !item.getType().equals(Material.AIR))
	    {
	    	if(plugin.getActionManager().actionRegisteredToItem(player, item))
	    	{
	    		physical = !action.toLowerCase().equals("left");
	    		action = action.replace("p", "");   		
	    		
	    		if(startedCombo.containsKey(uuid))
		    	{
		    		Combo plrCombo = startedCombo.get(uuid);
		    		plrCombo.setPhysical(physical);
		    		
		    		if(plrCombo.isShift() != isShift || !playerTimer.get(uuid).passesTimers())
		    		{
		    			removeComboinProgress(uuid);
		    			registerCombo(player, action, item, isShift);
		    			return;
		    		}		    	
		    		
		    		ItemMeta meta = item.getItemMeta();		    		
		    		if(item.equals(plrCombo.getItem()) && plrCombo.getItemMeta().equals(meta))
		    		{ 
		    			soundManager.playSound(player);

		    			if(plrCombo.getSecondAction().isEmpty())
		    			{
		    				plrCombo.setSecondAction(action);
		    				startedCombo.put(uuid, plrCombo);
		    				playerTimer.get(uuid).startSecondTimer();
		            
		    				plugin.getComboActionBar().preActionBar(player, "second", startedCombo.get(uuid));		    				
		    			}
		    			else
		    			{
		    				startedCombo.get(uuid).setThirdAction(action);
		    				startedCombo.get(uuid).callEvent();
		    				
		    				plugin.getComboActionBar().preActionBar(player, "third", startedCombo.get(uuid));
			    			removeComboinProgress(uuid);
		    			}
		    		}
		    	}
		    	else
		    	{
		    		Combo plrCombo = new Combo(plugin, player, item, action, isShift);
		    		plrCombo.setPhysical(physical);
		    		startedCombo.put(uuid, plrCombo);
		    		
		    		playerTimer.get(uuid).startFirstTimer();
		    		
		    		soundManager.playSound(player);	
		    		plugin.getComboActionBar().preActionBar(player, "first", startedCombo.get(uuid));
		    	}
	    	}
	    }
	}

	public Combo getComboinProgress(UUID uuid)
	{
		if (startedCombo.containsKey(uuid))
	    {
			return startedCombo.get(uuid);
	    }
	    return null;
	}
	public void removeComboinProgress(UUID uuid)
	{
	    if(startedCombo.containsKey(uuid))
	    {
	    	startedCombo.put(uuid, null);
	    	startedCombo.remove(uuid);
	    }
	    playerTimer.get(uuid).resetTimer();
	}
	
	public void addTimer(UUID uuid)
	{
		playerTimer.put(uuid, new ComboTimer(plugin));
	}
	public void removeTimer(UUID uuid)
	{
		if(playerTimer.containsKey(uuid))
			playerTimer.remove(uuid);
	}
	  




}
