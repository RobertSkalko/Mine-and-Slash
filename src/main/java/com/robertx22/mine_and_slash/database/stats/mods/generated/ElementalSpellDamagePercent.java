package com.robertx22.mine_and_slash.database.stats.mods.generated;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.ElementalStatMod;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.wrappers.MapWrapper;

public class ElementalSpellDamagePercent extends ElementalStatMod {

    public ElementalSpellDamagePercent(Elements element) {
        super(element);
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new ElementalSpellDamagePercent(element);
    }

    @Override
    public MapWrapper<Elements, ElementalSpellDamage> getBaseStatMap() {
        return ElementalSpellDamage.MAP;
    }


    @Override
    public float Min() {
        return 2;
    }

    @Override
    public float Max() {
        return 10;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Percent;
    }

}

