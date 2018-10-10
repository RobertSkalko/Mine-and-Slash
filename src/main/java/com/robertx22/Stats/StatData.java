package com.robertx22.stats;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.robertx22.database.Stats;
import com.robertx22.enums.*;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;

public class StatData {

	public Stat GetBase() {
		return Stats.Classes.get(StatRef);
	}

	public StatData(StatRefs statRef, StatTypes type, int value) {
		super();
		StatRef = statRef;
		Type = type;
		Value = value;
	}

	public StatRefs StatRef;
	public StatTypes Type;
	public int Value;

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