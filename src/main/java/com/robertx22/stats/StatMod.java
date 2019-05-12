package com.robertx22.stats;

import java.util.Random;

import com.robertx22.database.IGUID;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.utilityclasses.IWeighted;

public abstract class StatMod implements IWeighted, IGUID {

    @Override
    public int Weight() {
	return IWeighted.UncommonWeight;
    }

    private static Random ran = new Random();

    public abstract Stat GetBaseStat();

    public abstract float Min();

    public abstract float Max();

    public abstract StatTypes Type();

    public abstract String GUID();

    public int GetValueByPercent(int percent) {

	return (int) GetFloatByPercent(percent);

    }

    public float GetFloatByPercent(int percent) {

	return (Min() + (Max() - Min()) * percent / 100);

    }

    public int GetRandomNumber() {

	int max = Math.round(Max() - Min() + 1);

	return (int) (Min() + ran.nextInt(max));

    }

}