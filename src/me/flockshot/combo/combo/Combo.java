package me.flockshot.combo.combo;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.flockshot.combo.ComboPlugin;
import me.flockshot.combo.events.PlayerComboEvent;
import me.flockshot.combo.events.PlayerPreComboEvent;


public class Combo
{

	private Player player = null;
	private ItemStack item = null;
	private ItemMeta meta = null;
	private String firstaction = "";
	private String secondaction = "";
	private String thirdaction = "";
	private boolean shift;
	private boolean physical = false;
	private ComboType type;
	private ComboPlugin plugin;
	
	public Combo(ComboPlugin plugin, Player plr, ItemStack item, String action, boolean shift)
	{
		this.plugin = plugin;
		
		setFirstAction(action);
		setPlayer(plr);
		setItem(item);
		setItemMeta(item.getItemMeta());
		setShift(shift);
		
		PlayerPreComboEvent comboevent = new PlayerPreComboEvent(player, false);
		Bukkit.getPluginManager().callEvent(comboevent);
		if(comboevent.isCancelled())
		{
			plugin.getComboManager().removeComboinProgress(plr.getUniqueId());
		}
	}

	public Player getPlayer() {
		return player;
	}
	void setPlayer(Player plr) {
		player = plr;
	}

	public ItemStack getItem() {
		return item;
	}	
	void setItem(ItemStack item) {
		this.item = item;
	}
	
	public String getFirstAction() {
		return firstaction;
	}
	
	void setFirstAction(String action) {
		firstaction = action + " ";		
	}
	
	public String getSecondAction() {
		return secondaction;
	}	
	void setSecondAction(String action) {
		secondaction = action + " ";
	}
	
	public String getThirdAction() {
		return thirdaction;
	}
	void setThirdAction(String action)
	{
		thirdaction = action;
		
		setType(convertToType());
	}
	
	private ComboType convertToType()
	{
		char first = getFirstAction().toUpperCase().charAt(0);
		char second = getSecondAction().toUpperCase().charAt(0);
		char third = getThirdAction().toUpperCase().charAt(0);		

		return isShift() ? ComboType.valueOf("S"+first+second+third) : ComboType.valueOf(""+first+second+third+"");
	}

	public String getWholeCombo() {
		return getFirstAction() + getSecondAction() + getThirdAction();
	}
	
	void callEvent()
	{		
		PlayerComboEvent comboevent = new PlayerComboEvent(player, convertToType(), this);
		Bukkit.getPluginManager().callEvent(comboevent);
		if(comboevent.isCancelled())
			return;
		
		processEvent();		
	}
	private void processEvent()
	{
		plugin.getActionManager().callAction(getType(), getPlayer(), getItem(), isPhysical());
	}
	
	
	public boolean isShift() {
		return shift;
	}
	public void setShift(boolean shift) {
		this.shift = shift;
	}

	public ComboType getType() {
		return type;
	}
	public void setType(ComboType type) {
		this.type = type;
	}

	public ItemMeta getItemMeta() {
		return meta;
	}
	public void setItemMeta(ItemMeta meta) {
		this.meta = meta;
	}
	

	public boolean isPhysical()	{
		return physical;
	}
	public void setPhysical(boolean physical) {
		this.physical = physical;
	}
}
