package com.robertx22.mine_and_slash.saveclasses;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IApplyableStats;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class ExactStatData implements IApplyableStats {

    public ExactStatData() {

    }

    public ExactStatData(float value, StatTypes type, String statGUID) {
        this.value = value;
        this.type = type;
        this.statGUID = statGUID;
    }

    @Store
    private float value = 0;

    @Store
    private StatTypes type = StatTypes.Flat;

    @Store
    private String statGUID = "";

    @Override
    public void applyStats(EntityCap.UnitData data) {
        data.getUnit().getStat(statGUID).addExact(type, value);
    }
}
