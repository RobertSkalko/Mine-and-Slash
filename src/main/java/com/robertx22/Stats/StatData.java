package com.robertx22.stats;

import com.robertx22.classes.IHasBaseClass;
import com.robertx22.enums.*;

public class StatData implements IHasBaseClass {


	public StatData(StatRefs statRef, StatTypes type, int value) {
		super();
		StatRef = statRef;
		Type = type;
		Value = value;
	}
	
	public StatRefs StatRef;
	public StatTypes Type;
	public int Value;
	
	
	public Class Base;	
	@Override
	public Class<?> BaseClass() {
		
		return Base;
	}

	/*
	 * public Double CalcVal() {
	 * 
	 * double finalValue = 0;
	 * 
	 * finalValue += Flat;
	 * 
	 * finalValue *= 1 + Percent / 100;
	 * 
	 * finalValue *= 1 + Multi / 100;
	 * 
	 * return finalValue;
	 * 
	 * }
	 */

}