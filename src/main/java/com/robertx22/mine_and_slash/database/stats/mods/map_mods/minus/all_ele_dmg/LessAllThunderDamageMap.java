package com.robertx22.mine_and_slash.database.stats.mods.map_mods.minus.all_ele_dmg;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.generated.AllElementalDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public class LessAllThunderDamageMap extends BaseAllEleDmgMap {

    @Override
    public Stat GetBaseStat() {
        return new AllElementalDamage(Elements.Thunder);
    }

    @Override
    public String GUID() {
        return "LessAllThunderDamageMap";
    }

}
