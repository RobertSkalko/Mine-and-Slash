package com.robertx22.stats;

import java.util.ArrayList;

import com.robertx22.database.IGUID;
import com.robertx22.saveclasses.StatData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.enumclasses.Elements;

public abstract class Stat implements IGUID {

    public Stat() {
    }

    @Override
    public String GUID() {
	return Guid();
    }

    public String Name() {
	return Guid();
    }

    public int MaximumPercent = 0;

    public int MinimumAmount = 0;

    public boolean hasMinimumAmount = true;

    public int StatMinimum = 0;

    public abstract boolean IsPercent();

    public abstract String Guid();

    public abstract boolean ScalesToLevel();

    public abstract Elements Element();

    public int BaseFlat = 0;

    public int CalcVal(StatData data, UnitData Source) {

	float finalValue = 0 + BaseFlat;

	finalValue += StatMinimum;

	if (ScalesToLevel()) {
	    finalValue *= Source.getLevel();
	}

	finalValue += data.Flat;

	finalValue *= 1 + data.Percent / 100;

	finalValue *= 1 + data.Multi / 100;

	if (hasMinimumAmount && finalValue < this.MinimumAmount) {
	    finalValue = this.MinimumAmount;
	}

	if (this.IsPercent() && MaximumPercent > 0 && finalValue > MaximumPercent) {
	    finalValue = MaximumPercent;
	}

	data.Value = finalValue;

	return (int) finalValue;

    }

    public ArrayList<IStatEffect> Effects;

    public boolean IsShownOnTooltip() {
	return true;
    }

}
