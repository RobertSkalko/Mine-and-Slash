package com.robertx22.mine_and_slash.uncommon.interfaces;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.stats.TransferMethod;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;

import java.util.List;

public interface IStatTransfer extends IGUID {

    public abstract List<TransferMethod> Transfer();

    public default void transferStats(Unit copy, Unit unit, StatData data) {

        for (TransferMethod stat : this.Transfer()) {

            float val = copy.peekAtStat(stat.converted.GUID()).Flat * data.val /* percent */ / 100;

            if (val != 0) {
                unit.getCreateStat(stat.converted).Flat -= val;
                unit.getCreateStat(stat.statThatBenefits).Flat += val;
            }
        }

    }

}
