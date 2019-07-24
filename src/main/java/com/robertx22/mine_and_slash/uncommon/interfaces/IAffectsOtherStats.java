package com.robertx22.mine_and_slash.uncommon.interfaces;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;

import java.util.ArrayList;
import java.util.List;

public interface IAffectsOtherStats {

    /**
     * This is done after stats are calculated, as a last stat changing method
     */
    public default void TryAffectOtherStats(UnitData unit, StatData data) {

        for (StatModData mod : getStatsMods()) {

            mod.useOnPlayer(unit);
        }

    }

    public default int percent() {
        return 100;
    }

    public List<StatMod> getStats();

    public default List<StatModData> getStatsMods() {

        List<StatModData> list = new ArrayList<StatModData>();

        for (StatMod mod : getStats()) {
            list.add(StatModData.Load(mod, percent()));
        }

        return list;

    }
}
