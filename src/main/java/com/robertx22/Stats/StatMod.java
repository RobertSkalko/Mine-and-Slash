package com.robertx22.stats;

import java.util.Random;

import com.robertx22.enums.StatTypes;
import com.robertx22.saveclasses.Unit;

import net.minecraft.entity.EntityLivingBase;

public abstract class StatMod {

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

	public String NameText() {
		Stat basestat = GetBaseStat();

		return basestat.Name() + ": ";
	}

	public String NameAndValueText(Unit Source, EntityLivingBase entity) {
		Stat basestat = GetBaseStat();

		return NameText() + basestat.GetValue(Source, entity);
	}

	public String ToTooltipString(Unit Source, EntityLivingBase entity) {

		Stat basestat = GetBaseStat();

		String text = NameAndValueText(Source, entity);

		if (Type() == StatTypes.Flat) {

			if (basestat.IsPercent()) {
				text += "%";
			}

		} else if (Type() == StatTypes.Percent) {
			text += "%";
		} else {
			text += "% Multi";
		}

		return text;

	}
}