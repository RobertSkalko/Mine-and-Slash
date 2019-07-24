package com.robertx22.mine_and_slash.database.map_affixes.detrimental.ele;

import com.robertx22.mine_and_slash.database.map_affixes.DetrimentalMapAffix;
import com.robertx22.mine_and_slash.database.stats.stat_mods.map_mods.minus.all_ele_dmg.LessAllThunderDamageMap;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

import java.util.Arrays;
import java.util.List;

public class LessAllThunderDmgAffix extends DetrimentalMapAffix {

    @Override
    public String GUID() {
        return "LessAllThunderDmgAffix";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new LessAllThunderDamageMap(), percent));
    }

}
