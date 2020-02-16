package com.robertx22.mine_and_slash.database.stats.mods.generated;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class HighElementalResistFlat extends ElementalResistFlat {
    public HighElementalResistFlat(Elements element) {
        super(element);
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new ElementalResistFlat(element);
    }

    @Override
    public float Min() {
        return super.Min() * 2F;
    }

    @Override
    public float Max() {
        return super.Max() * 2;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public String GUID() {
        return "high_" + element.guidName + "_resist_flat";
    }
}
