package me.flockshot.combo.utils;

import java.text.NumberFormat;
import java.text.ParsePosition;

import me.flockshot.combo.deletelater.RequirementType;

public class NumberUtility
{
	
	
	public boolean calculateNumberComparison(String value, String compareWith, RequirementType type)
	{
		
		if(isNum(value) && isNum(compareWith))
		{
			double val = Double.valueOf(value);
			double comp = Double.valueOf(compareWith);
			
			switch(type)
			{
				case GREATER:
					return val>comp;
				case GREATEREQUAL:
					return val>=comp;
				case LESSEREQUAL:
					return val<=comp;
				case LESSER:
					return val<comp;
				case EQUAL:
					return val==comp;
				case NOTEQUAL:
					return val!=comp;
				default:
					return false;
				
			}
		
			
		}
		else
			return false;
		
	}
	
	public boolean isNum(String str)
	{
	  NumberFormat formatter = NumberFormat.getInstance();
	  ParsePosition pos = new ParsePosition(0);
	  formatter.parse(str, pos);
	  return str.length() == pos.getIndex();
	}
}
