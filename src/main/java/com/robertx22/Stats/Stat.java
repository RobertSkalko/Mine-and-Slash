package com.robertx22.stats;

import java.util.ArrayList;

import com.robertx22.database.IGUID;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.enumclasses.Elements;

public abstract class Stat implements IGUID {

	public Stat() {
	}

	@Override
	public String GUID() {
		return Name();
	}

	public int StatMinimum = 0;

	public abstract boolean IsPercent();

	public abstract String Name();

	public abstract boolean ScalesToLevel();

	public abstract Elements Element();

	public int BaseFlat = 0;

	public int CalcVal(StatData data, Unit Source) {

		float finalValue = 0;

		finalValue += StatMinimum + data.BaseFlat;

		if (ScalesToLevel()) {
			finalValue *= Source.level;
		}

		finalValue += data.Flat;

		finalValue *= 1 + data.Percent / 100;

		finalValue *= 1 + data.Multi / 100;

		if (finalValue < 0) {
			finalValue = 0;
		}

		data.Value = finalValue;

		return (int) finalValue;

	}

	public ArrayList<IStatEffect> Effects;

	public boolean IsShownOnTooltip() {
		return true;
	}

}
