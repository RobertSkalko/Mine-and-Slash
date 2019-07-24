package com.robertx22.mine_and_slash.uncommon.interfaces;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.stats.ConversionMethod;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;

import java.util.List;

public interface IStatConversion extends IGUID {

    public abstract List<ConversionMethod> conversion();

    public default void convertStats(Unit copy, Unit unit, StatData data) {

        for (ConversionMethod stat : this.conversion()) {

            float val = copy.getStat(stat.converted).Flat * data.Value /* percent */ / 100;

            unit.getStat(stat.statThatBenefits).Flat += val;

        }

    }

}
