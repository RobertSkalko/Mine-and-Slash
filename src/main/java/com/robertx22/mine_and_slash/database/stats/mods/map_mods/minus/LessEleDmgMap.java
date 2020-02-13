package com.robertx22.mine_and_slash.database.stats.mods.map_mods.minus;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.stats.mods.ElementalStatMod;
import com.robertx22.mine_and_slash.database.stats.types.generated.AllElementalDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import com.robertx22.mine_and_slash.uncommon.wrappers.MapWrapper;

public class LessEleDmgMap extends ElementalStatMod {

    public LessEleDmgMap(Elements element) {
        super(element);

    }

    @Override
    public MapWrapper getBaseStatMap() {
        return AllElementalDamage.MAP;
    }

    @Override
    public float Min() {
        return -30;
    }

    @Override
    public float Max() {
        return -75;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public String GUID() {
        return "less_all_" + element.guidName + "_damage_map";
    }

    @Override
    public IGUID newGeneratedInstance(Elements element) {
        return new LessEleDmgMap(element);
    }
}
