package com.robertx22.mine_and_slash.uncommon.interfaces;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.stats.ConversionMethod;
import com.robertx22.mine_and_slash.saveclasses.unit.StatData;
import com.robertx22.mine_and_slash.saveclasses.unit.Unit;

import java.util.List;

public interface IStatConversion extends IGUID {

    public abstract List<ConversionMethod> conversion();

    public default void convertStats(Unit copy, Unit unit, StatData data) {

        for (ConversionMethod stat : this.conversion()) {

            float val = copy.getCreateStat(stat.converted)
                .getFlatAverage() * data.getAverageValue() /* percent */ / 100;
            unit.getCreateStat(stat.statThatBenefits)
                .addFlat(val);

        }

    }

}
