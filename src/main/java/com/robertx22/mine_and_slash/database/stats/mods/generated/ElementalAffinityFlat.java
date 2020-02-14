package com.robertx22.mine_and_slash.database.stats.mods.generated;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.ElementalStatMod;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalAffinity;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import com.robertx22.mine_and_slash.uncommon.wrappers.MapWrapper;

public class ElementalAffinityFlat extends ElementalStatMod {

    public ElementalAffinityFlat(Elements element) {
        super(element);

    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new ElementalAffinityFlat(element);
    }

    @Override
    public MapWrapper<Elements, ElementalAffinity> getBaseStatMap() {
        return ElementalAffinity.MAP;
    }

    @Override
    public float Min() {
        return 4;
    }

    @Override
    public float Max() {
        return 12;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public String GUID() {
        return element.guidName + "_affinity_flat";
    }

}
