package com.robertx22.mine_and_slash.database.stats.mods.generated;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.ElementalStatMod;
import com.robertx22.mine_and_slash.database.stats.types.generated.AllElementalDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.wrappers.MapWrapper;

public class AllElementalDamageMulti extends ElementalStatMod {

    public AllElementalDamageMulti(Elements element) {
        super(element);
    }

    @Override
    public MapWrapper<Elements, AllElementalDamage> getBaseStatMap() {
        return AllElementalDamage.MAP;
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new AllElementalDamageMulti(element);
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
        return StatModTypes.Flat;
    }

}


