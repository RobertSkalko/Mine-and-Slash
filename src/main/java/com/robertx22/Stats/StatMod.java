package com.robertx22.stats;

import java.util.Random;

import com.robertx22.enums.StatRefs;
import com.robertx22.enums.StatTypes;
import com.robertx22.saving.IHasBaseClass;

public abstract class StatMod implements IHasBaseClass{	
	
	private static Random ran = new Random();

	public abstract int Min();

	public abstract int Max();
	
	public abstract StatTypes Type();
	

	
	public int GetValueByPercent(int percent) {
		
		return Min() + (Max() - Min()) * percent / 100;
		
	}
	
	public int GetRandomNumber() {

		int max = Math.round(Max() - Min() + 1);

		return Min() + ran.nextInt(max);

	}

}
