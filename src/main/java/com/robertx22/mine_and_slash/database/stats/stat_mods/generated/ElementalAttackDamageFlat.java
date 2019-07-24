package com.robertx22.mine_and_slash.database.stats.stat_mods.generated;

import com.robertx22.mine_and_slash.database.ElementalStatMod;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.generated.ElementalAttackDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class ElementalAttackDamageFlat extends ElementalStatMod {

    public ElementalAttackDamageFlat(Elements element) {
        super(element);

    }

    @Override
    public Stat GetBaseStat() {
        return new ElementalAttackDamage(this.element);
    }

    @Override
    public float Min() {
        return 3;
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
        return "Attack" + element.name() + "DamageFlat";
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new ElementalAttackDamageFlat(element);
    }
}
