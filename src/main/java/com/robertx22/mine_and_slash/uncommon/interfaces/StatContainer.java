package com.robertx22.mine_and_slash.uncommon.interfaces;

import com.robertx22.mine_and_slash.saveclasses.gearitem.BaseStatContainer;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IApplyableStats;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;

import java.util.ArrayList;
import java.util.List;

public class StatContainer implements IApplyableStats {

    public List<BaseStatContainer> list = new ArrayList<>();

    public StatContainer(List<BaseStatContainer> list) {
        this.list = list;
    }

    public StatContainer(BaseStatContainer... list) {
        for (BaseStatContainer t : list) {
            this.list.add(t);
        }
    }

    @Override
    public void applyStats(EntityCap.UnitData data, int level) {

        list.forEach(x -> x.applyStats(data, level));

    }
}
