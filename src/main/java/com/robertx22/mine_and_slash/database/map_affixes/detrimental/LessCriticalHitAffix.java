package com.robertx22.mine_and_slash.database.map_affixes.detrimental;

import com.robertx22.mine_and_slash.database.map_affixes.DetrimentalMapAffix;
import com.robertx22.mine_and_slash.database.stats.stat_mods.map_mods.minus.LessCriticalHitMap;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

import java.util.Arrays;
import java.util.List;

public class LessCriticalHitAffix extends DetrimentalMapAffix {

    @Override
    public String GUID() {
        return "LessCriticalHitAffix";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new LessCriticalHitMap(), percent));
    }

}
