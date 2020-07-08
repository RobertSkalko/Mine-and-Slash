package com.robertx22.mine_and_slash.database.stats.mods.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.ElementalStatMod;
import com.robertx22.mine_and_slash.database.stats.types.generated.WeaponDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.wrappers.MapWrapper;

public class ElementalAttackDamageFlat extends ElementalStatMod {

    public ElementalAttackDamageFlat(Elements element) {
        super(element);

    }

    @Override
    public final Stat GetBaseStat() {
        return new WeaponDamage(element);
    }

    @Override
    public MapWrapper<Elements, WeaponDamage> getBaseStatMap() {
        return WeaponDamage.MAP;
    }

    @Override
    public float Min() {
        return 3;
    }

    @Override
    public float Max() {
        return 7;
    }

    @Override
    public float minSecond() {
        return 6;
    }

    @Override
    public float maxSecond() {
        return 12;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new ElementalAttackDamageFlat(element);
    }
}
