package com.robertx22.mine_and_slash.database.stats.stat_mods.generated;

import com.robertx22.mine_and_slash.database.ElementalStatMod;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class ElementalSpellDamageMulti extends ElementalStatMod {

    public ElementalSpellDamageMulti(Elements element) {
        super(element);
    }

    @Override
    public Stat GetBaseStat() {
        return new ElementalSpellDamage(this.element);
    }

    @Override
    public float Min() {
        return 5;
    }

    @Override
    public float Max() {
        return 15;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Multi;
    }

    @Override
    public String GUID() {
        return "Spell" + element.name() + "DamageMulti";
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new ElementalSpellDamageMulti(element);
    }
}

