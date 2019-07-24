package com.robertx22.mine_and_slash.database.stats.stat_mods.map_mods.minus.all_ele_dmg;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_types.generated.AllElementalDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public class LessAllFireDamageMap extends BaseAllEleDmgMap {

    @Override
    public Stat GetBaseStat() {
        return new AllElementalDamage(Elements.Fire);
    }

    @Override
    public String GUID() {
        return "LessAllFireDamageMap";
    }

}
