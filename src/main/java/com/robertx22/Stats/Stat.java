package com.robertx22.stats;

import java.util.ArrayList;

import com.robertx22.enums.Elements;
import com.robertx22.enums.StatTypes;
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

	public void Add(int num, StatTypes type) {

		if (type == StatTypes.Flat) {
			Flat += num;
		} else if (type == StatTypes.Percent) {
			Percent += num;
		} else if (type == StatTypes.Multi) {
			Multi += num;
		}
	}

	public int GetActualVal() {

		double finalValue = 0;

		finalValue += Flat + StatMinimum;

		finalValue *= 1 + Percent / 100;

		finalValue *= 1 + Multi / 100;

		return (int) finalValue;

	}

	public Double Value = (double) 0;

	public Double GetValue(Unit Source) {

		if (Source.StatsDirty) {
			Source.RecalculateStats();
		}

		return Value;

	}

	public ArrayList<IStatEffect> Effects;

	public boolean IsShownOnTooltip() {
		return true;
	}

}
