package com.robertx22.mine_and_slash.database.stats.mods.generated;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class MediumElementalAttackDamageFlat extends ElementalAttackDamageFlat {

    public MediumElementalAttackDamageFlat(Elements element) {
        super(element);
    }

    @Override
    public float Min() {
        return super.Min() * 0.7F;
    }

    @Override
    public float Max() {
        return super.Max() * 0.7F;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public String GUID() {
        return "medium_attack_" + element.guidName + "_damage_flat";
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new MediumElementalAttackDamageFlat(element);
    }
}
