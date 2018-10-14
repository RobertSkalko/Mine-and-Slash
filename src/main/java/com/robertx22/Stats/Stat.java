package com.robertx22.stats;

import java.util.ArrayList;

import com.robertx22.enums.Elements;
import com.robertx22.enums.StatTypes;
import com.robertx22.saveclasses.StatModData;
import com.robertx22.saveclasses.Unit;

public abstract class Stat {

	public int StatMinimum = 0;

	public abstract boolean IsPercent();

	public abstract String Name();

	public abstract boolean ScalesToLevel();

	public abstract Elements Element();

	public int Flat = 0;
	public int Percent = 0;
	public int Multi = 0;

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

	public int CalcVal() {

		float finalValue = 0;

		finalValue += Flat + StatMinimum;

		finalValue *= 1 + Percent / 100;

		finalValue *= 1 + Multi / 100;

		this.Value = finalValue;

		return (int) finalValue;

	}

	public float Value;

	public float GetValue(Unit Source) {

		if (Source.StatsDirty) {
			Source.RecalculateStats();
		}

		return Value;

	}

	public int GetInt(Unit Source) {
		return (int) GetValue(Source);
	}

	public ArrayList<IStatEffect> Effects;

	public boolean IsShownOnTooltip() {
		return true;
	}

}
