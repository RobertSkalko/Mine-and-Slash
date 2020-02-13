package com.robertx22.mine_and_slash.database.map_affixes.detrimental;

import com.robertx22.mine_and_slash.database.map_affixes.DetrimentalMapAffix;
import com.robertx22.mine_and_slash.database.stats.mods.map_mods.minus.LessEnergyRegenMap;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

import java.util.Arrays;
import java.util.List;

public class LessEnergyRegenAffix extends DetrimentalMapAffix {

    @Override
    public String GUID() {
        return "less_energy_regen_affix";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new LessEnergyRegenMap(), percent));
    }

    @Override
    public float lootMulti() {
        return 2;
    }

}
