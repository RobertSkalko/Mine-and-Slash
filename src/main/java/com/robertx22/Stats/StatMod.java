package com.robertx22.Stats;

import java.util.Random;

import com.robertx22.Enums.StatRefs;


public abstract class StatMod {
	

	private static Random ran = new Random();

	public abstract StatRefs StatRef();
	public abstract int Min();
	public abstract int Max();	
	
	public Stat GetBase() {
	return Stats.Classes.get(StatRef());
	}
	
	
    public int GetRandomNumber() {

    	int max = Math.round(Max() - Min() + 1);
    	
        return  Min() + ran.nextInt(max);

    }

  	
}
