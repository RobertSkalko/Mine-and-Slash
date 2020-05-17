package com.robertx22.mine_and_slash.database.stats.mods.percent;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.ElementalStatMod;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalAttackDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.wrappers.MapWrapper;

public class ElementalAttackDamagePercent extends ElementalStatMod {

    public ElementalAttackDamagePercent(Elements element) {
        super(element);
    }

    @Override
    public final Stat GetBaseStat() {
        return new ElementalAttackDamage(element);
    }

    @Override
    public MapWrapper<Elements, ElementalAttackDamage> getBaseStatMap() {
        return ElementalAttackDamage.MAP;
    }

    @Override
    public float Min() {
        return 3;
    }

    @Override
    public float Max() {
        return 6;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Percent;
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new ElementalAttackDamagePercent(element);
    }
}
