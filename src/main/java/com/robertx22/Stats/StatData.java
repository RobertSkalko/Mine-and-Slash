package com.robertx22.stats;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.robertx22.Enums.*;
import com.robertx22.database.Stats;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;

public class StatData {

	public StatData() {
	}

	public Stat GetBase() {
		return Stats.Classes.get(StatRef);
	}
	
	public StatRefs StatRef;	
	public StatTypes Type;
	public int Value;
	
	
/*
	public Double CalcVal() {

		double finalValue = 0;

		finalValue += Flat;

		finalValue *= 1 + Percent / 100;

		finalValue *= 1 + Multi / 100;

		return finalValue;

	}
	*/

}