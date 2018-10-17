package com.robertx22.stats;

import java.util.Random;

import com.robertx22.classes.IWeighted;
import com.robertx22.enumclasses.StatTypes;

public abstract class StatMod implements IWeighted {

	private static Random ran = new Random();

	public abstract Stat GetBaseStat();

	public abstract int Min();

	public abstract int Max();

	public abstract StatTypes Type();

	public abstract String GUID();

	public int GetValueByPercent(int percent) {

		return Min() + (Max() - Min()) * percent / 100;

	}

	public int GetRandomNumber() {

		int max = Math.round(Max() - Min() + 1);

		return Min() + ran.nextInt(max);

	}

}