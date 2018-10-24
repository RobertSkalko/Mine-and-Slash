package com.robertx22.stats;

import java.util.ArrayList;

import com.robertx22.saveclasses.Unit;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.enumclasses.StatTypes;

import net.minecraft.entity.EntityLivingBase;

public abstract class Stat {

	public Stat() {
	}

	public int StatMinimum = 0;

	public abstract boolean IsPercent();

	public abstract String Name();

	public abstract boolean ScalesToLevel();

	public abstract Elements Element();

	public int BaseFlat = 0;

	public float Flat = 0;
	public float Percent = 0;
	public float Multi = 0;

	public void Clear() {
		Flat = 0;
		Percent = 0;
		Multi = 0;
	}

	public void Add(StatModData mod) {

		if (mod.type == StatTypes.Flat) {
			Flat += mod.GetActualVal();
		} else if (mod.type == StatTypes.Percent) {
			Percent += mod.GetActualVal();
		} else if (mod.type == StatTypes.Multi) {
			Multi += mod.GetActualVal();
		}
	}

	public int CalcVal(Unit Source) {

		float finalValue = 0;

		finalValue += StatMinimum + BaseFlat;

		if (ScalesToLevel()) {
			finalValue *= Source.level;
		}

		finalValue += Flat;

		finalValue *= 1 + Percent / 100;

		finalValue *= 1 + Multi / 100;

		this.Value = finalValue;

		return (int) finalValue;

	}

	public float Value;

	public float GetValue(Unit Source, EntityLivingBase entity) {

		if (Source.StatsDirty) {
			Source.RecalculateStats(entity);
		}

		return Value;

	}

	public int GetInt(Unit Source, EntityLivingBase entity) {
		return (int) GetValue(Source, entity);
	}

	public ArrayList<IStatEffect> Effects;

	public boolean IsShownOnTooltip() {
		return true;
	}

}
