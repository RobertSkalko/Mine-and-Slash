package com.robertx22.mine_and_slash.uncommon.interfaces;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface IAffectsOtherStats {

    /**
     * This is done after stats are calculated, as a last stat changing method
     */
    public default void TryAffectOtherStats(UnitData unit, StatData data) {
        this.getAllStatContainers()
            .applyStats(unit);
    }

    public default int percent() {
        return 100;
    }

    public List<StatModifier> getStats();

    public default List<ExactStatData> getExactStats() {
        return Arrays.asList();
    }

    public default StatContainer getAllStatContainers() {
        StatContainer con = new StatContainer();
        getStatsMods().forEach(x -> con.list.add(x));
        getExactStats().forEach(x -> con.list.add(x));
        return con;
    }

    public default List<ExactStatData> getStatsMods() {

        List<ExactStatData> list = new ArrayList<ExactStatData>();

        for (StatModifier mod : getStats()) {
            list.add(mod.ToExactStat(percent()));
        }

        return list;

    }
}
