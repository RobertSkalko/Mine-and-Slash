package com.robertx22.mine_and_slash.database.stats.mods.percent;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.ElementalStatMod;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalInfusion;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.wrappers.MapWrapper;

public class ElementalInfusionPercent extends ElementalStatMod {

    public ElementalInfusionPercent(Elements element) {
        super(element);
    }

    @Override
    public MapWrapper getBaseStatMap() {
        return ElementalInfusion.MAP;
    }

    @Override
    public final Stat GetBaseStat() {
        return new ElementalInfusion(element);
    }

    @Override
    public float Min() {
        return 5;
    }

    @Override
    public float Max() {
        return 20;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Percent;
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new ElementalInfusionPercent(element);
    }

}
