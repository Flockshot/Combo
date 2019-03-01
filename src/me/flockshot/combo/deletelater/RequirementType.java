package me.flockshot.combo.deletelater;

public enum RequirementType
{
	PERMISSION,
	COOLDOWN,
	GREATER,
	LESSER,
	GREATEREQUAL,
	LESSEREQUAL,
	EQUAL,
	NOTEQUAL,
	STRINGEQUALS,
	STRINGEQUALSIGNORECASE,
	STRINGCONTAINS,
	STRINGCONTAINSIGNORECASE,
	ITEMNAMEEQUALS,
	ITEMNAMEEQUALSIGNORECASE,
	ITEMNAMECONTAINS,
	ITEMNAMECONTAINSIGNORECASE,
	
	JAVASCRIPT,
	ENCHANT;
	
	
	public static RequirementType convertToType(String type)
	{
		// TODO Auto-generated method stub
		type = type.toLowerCase();
		switch(type)
		{
			//case "permission":
			//	return RequirementType.PERMISSION;
			//case "enchant":
			//	return RequirementType.ENCHANT;
			//case "cooldown":
			//	return RequirementType.COOLDOWN;
			//case ">":
			//	return RequirementType.GREATER;
			//case "<":
			//	return RequirementType.LESSER;
			//case ">=":
			//	return RequirementType.GREATEREQUAL;
			//case "<=":
			//	return RequirementType.LESSEREQUAL;
			//case "=":
			//	return RequirementType.EQUAL;
			//case "==":
			//	return RequirementType.EQUAL;
			//case "!=":
			//	return RequirementType.NOTEQUAL;
			//case "string equals":
			//	return RequirementType.STRINGEQUALS;
			//case "string equals ignorecase":
			//	return RequirementType.STRINGEQUALSIGNORECASE;
			//case "string contains":
			//	return RequirementType.STRINGCONTAINS;
			//case "string contains ignorecase":
			//	return RequirementType.STRINGCONTAINSIGNORECASE;
			case "itemname equals":
				return RequirementType.ITEMNAMEEQUALS;
			case "itemname equals ignorecase":
				return RequirementType.ITEMNAMEEQUALSIGNORECASE;
			case "itemname contains":
				return RequirementType.ITEMNAMECONTAINS;
			case "itemname contains ignorecase":
				return RequirementType.ITEMNAMECONTAINSIGNORECASE;
				
			
			case "javascript":
				return RequirementType.JAVASCRIPT;
		}
		
		
		return null;
	}
	
}
