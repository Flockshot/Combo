package me.flockshot.combo.action;

import java.util.HashMap;
import java.util.List;

import org.bukkit.inventory.ItemStack;

import me.flockshot.combo.combo.ComboType;
import me.flockshot.combo.requirement.Requirement;
import me.flockshot.combo.subactionmanager.SubAction;


public class Action
{
	private String actionName;
	private ItemStack item;
	private List<SubAction> actions;
	private List<Requirement> globalReqs;
	private HashMap<ComboType, SubAction> combos = new HashMap<ComboType, SubAction>(); 
	private boolean isRight = false;
	private boolean isLeft = false;
	
	public Action(String actionname, ItemStack item, List<Requirement> globalReqs, List<SubAction> actions)
	{
		setItem(item);
		setName(actionname);
		setSubActions(actions);		
		setGlobalRequirements(globalReqs);
	}

	public ItemStack getItem() {
		return item;
	}
	private void setItem(ItemStack item) {
		this.item = item;
	}
	
	public String getName() {
        return actionName;
    }
	public void setName(String name) {
		actionName = name;
	}

	public SubAction getSubActionFromType(ComboType type) {
        return combos.get(type);
    }
	public List<SubAction> getSubActions() {
		return actions;
	}
	
	public void setSubActions(List<SubAction> actions)
    {
        this.actions = actions;
        for(SubAction subAct : actions)
        {
            combos.put(subAct.getComboType(), subAct);
        }
    }
		
	public List<Requirement> getGlobalRequirements() {
		return globalReqs;
	}
	public void setGlobalRequirements(List<Requirement> reqs) {
		globalReqs = reqs;
	}

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean isRight) {
        this.isRight = isRight;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public void setLeft(boolean isLeft) {
        this.isLeft = isLeft;
    }
		

}
