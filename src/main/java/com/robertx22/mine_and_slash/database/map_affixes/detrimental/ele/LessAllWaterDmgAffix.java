package com.robertx22.mine_and_slash.database.map_affixes.detrimental.ele;

import com.robertx22.mine_and_slash.database.map_affixes.DetrimentalMapAffix;
import com.robertx22.mine_and_slash.database.stats.stat_mods.map_mods.minus.all_ele_dmg.LessAllWaterDamageMap;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

import java.util.Arrays;
import java.util.List;

public class LessAllWaterDmgAffix extends DetrimentalMapAffix {

    @Override
    public String GUID() {
        return "LessAllWaterDmgAffix";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new LessAllWaterDamageMap(), percent));
    }

}
