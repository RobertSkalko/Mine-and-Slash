package com.robertx22.mine_and_slash.database.stats.mods.generated;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class HighElementalPeneFlat extends ElementalPeneFlat {
    public HighElementalPeneFlat(Elements element) {
        super(element);
    }

    @Override
    public float Min() {
        return super.Min();
    }

    @Override
    public float Max() {
        return super.Max();
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public String GUID() {
        return "high_" + element.guidName + "_pene_flat";
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new HighElementalPeneFlat(element);
    }

}
