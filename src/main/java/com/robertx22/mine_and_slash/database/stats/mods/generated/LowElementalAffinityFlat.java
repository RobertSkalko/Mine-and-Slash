package com.robertx22.mine_and_slash.database.stats.mods.generated;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.ElementalStatMod;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalAffinity;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import com.robertx22.mine_and_slash.uncommon.wrappers.MapWrapper;

public class LowElementalAffinityFlat extends ElementalStatMod {

    public LowElementalAffinityFlat(Elements element) {
        super(element);

    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new LowElementalAffinityFlat(element);
    }

    @Override
    public MapWrapper<Elements, ElementalAffinity> getBaseStatMap() {
        return ElementalAffinity.MAP;
    }

    @Override
    public float Min() {
        return 1;
    }

    @Override
    public float Max() {
        return 4;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public String GUID() {
        return "low_" + element.guidName + "_affinity_flat";
    }

}

