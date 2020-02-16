package com.robertx22.mine_and_slash.database.stats.mods.generated;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class LowElementalAttackDamageFlat extends ElementalAttackDamageFlat {

    public LowElementalAttackDamageFlat(Elements element) {
        super(element);
    }

    @Override
    public float Min() {
        return super.Min() * 0.25F;
    }

    @Override
    public float Max() {
        return super.Max() * 0.25F;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public String GUID() {
        return "low_attack_" + element.guidName + "_damage_flat";
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new LowElementalAttackDamageFlat(element);
    }

}
