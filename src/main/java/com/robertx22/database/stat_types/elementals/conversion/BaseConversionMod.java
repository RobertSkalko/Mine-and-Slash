package com.robertx22.database.stat_types.elementals.conversion;

import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.ConversionMethod;
import com.robertx22.stats.IStatConversion;
import com.robertx22.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public abstract class BaseConversionMod extends Stat implements IStatConversion {

    public BaseConversionMod() {
	this.MaximumPercent = 100;
    }

    @Override
    public boolean IsPercent() {
	return true;
    }

    @Override
    public boolean ScalesToLevel() {
	return false;
    }

    @Override
    public Elements Element() {
	return null;
    }

    @Override
    public void convertStats(Unit copy, Unit unit, StatData data) {

	for (ConversionMethod stat : this.conversion()) {

	    float val = copy.MyStats.get(stat.converted.Guid()).Flat * data.Value /* percent */ / 100;

	    unit.MyStats.get(stat.statThatBenefits.Guid()).Flat += val;

	}

    }

}
