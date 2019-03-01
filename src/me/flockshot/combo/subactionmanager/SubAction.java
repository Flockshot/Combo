package me.flockshot.combo.subactionmanager;

import java.util.List;
import me.flockshot.combo.combo.ComboType;
import me.flockshot.combo.executable.Executable;
import me.flockshot.combo.requirement.Requirement;

public class SubAction
{
	private ComboType comboType;
	private List<Requirement> requirements;
	private List<Executable> acceptance;
	//private String perm;
	//private String cooldownMsg;
	//private String triggerMsg;
	//private String permissionMsg;
	//private int cooldown;
	//private List<String> consoleCommands;
	//private List<String> playerCommands;
	//private Map<Enchantment, Integer> enchs;
	//private List<String> effects;
	//private Collection<PotionEffect> peffects;
	//private 
	
	public SubAction(ComboType combination, List<Requirement> reqs, List<Executable> executable)
	{
		setComboType(combination);
		setRequirements(reqs);
		setAcceptance(executable);
	}
	
	public ComboType getComboType()
	{
		return comboType;
	}
	public List<Executable> getAcceptance()
	{
		return acceptance;
	}
	public List<Requirement> getRequirements()
	{
		return requirements;
	}
	
	private void setComboType(ComboType combination)
	{
		comboType = combination;
	}
	public void setRequirements(List<Requirement> requirements)
	{
		this.requirements = requirements;
	}
	public void setAcceptance(List<Executable> acceptance)
	{
		this.acceptance = acceptance;
	}
	
	

	

	
	/*

	public Collection<PotionEffect> getPEffects()
	{
		return peffects;
	}

	public void setPEffects(Collection<PotionEffect> peffects)
	{
		this.peffects = peffects;
	}
	
	@SuppressWarnings("deprecation")
	private Collection<PotionEffect> convertPe(List<String> effects2)
	{
		// TODO Auto-generated method stub
		
		Collection<PotionEffect> pe = new ArrayList<PotionEffect>();
		Iterator<String> it = effects2.iterator();		

		while(it.hasNext())
		{
			String current = (String) it.next();					
			String[] split = current.split(":");					
			int dur = Integer.parseInt(split[1])*20;					
			int amp = Integer.parseInt(split[2]);
			amp--;
			
			PotionEffect eff;
			
			if(isNum(split[0]))
			{					
				eff = new PotionEffect(PotionEffectType.getById(Integer.parseInt(split[0])), dur, amp);
			}
			else
			{
				eff = new PotionEffect(PotionEffectType.getByName(split[0]), dur, amp);
			}
			pe.add(eff);								
		}
		return pe;
		
	}
	
	private void initialize()
	{
		setPerm(permission);
		setCooldown(convertCool(cool));
		setCCommands(cc);
		setPCommands(pc);
		setPEffects(convertPe(peffects));
		//setEffects(peffects);
		setCooldownMsg(coolMsg);
		setTriggerMsg(trigMsg);
		setPermissionMsg(permMsg);
		//TODO
		setEnchantsRequirement(enchs);
	}
	*/


}
