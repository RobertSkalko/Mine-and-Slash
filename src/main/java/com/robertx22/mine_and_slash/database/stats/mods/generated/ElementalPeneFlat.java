package com.robertx22.mine_and_slash.database.stats.mods.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.ElementalStatMod;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalPene;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.wrappers.MapWrapper;

public class ElementalPeneFlat extends ElementalStatMod {

    public ElementalPeneFlat(Elements element) {
        super(element);
    }

    @Override
    public MapWrapper<Elements, ElementalPene> getBaseStatMap() {
        return ElementalPene.MAP;
    }

    @Override
    public final Stat GetBaseStat() {
        return new ElementalPene(element);
    }

    @Override
    public float Min() {
        return 3;
    }

    @Override
    public float Max() {
        return 8;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new ElementalPeneFlat(element);
    }
}
