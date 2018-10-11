package com.robertx22.stats;

import com.robertx22.enums.*;
import com.robertx22.saving.IHasBaseClass;

public class StatData implements IHasBaseClass {


	public StatData(String baseclass, StatTypes type, int value) {
		Base = baseclass;
		Type = type;
		Value = value;
	}
	
	public StatTypes Type;
	public int Value;	
	public String Base;	
	
	@Override
	public String BaseClass() {
		
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