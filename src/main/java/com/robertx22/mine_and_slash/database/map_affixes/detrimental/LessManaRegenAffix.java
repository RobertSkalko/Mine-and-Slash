package com.robertx22.mine_and_slash.database.map_affixes.detrimental;

import com.robertx22.mine_and_slash.database.map_affixes.DetrimentalMapAffix;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ManaRegenPercent;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

import java.util.Arrays;
import java.util.List;

public class LessManaRegenAffix extends DetrimentalMapAffix {

    @Override
    public String GUID() {
        return "less_mana_regen_affix";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new ManaRegenPercent().size(StatMod.Size.LESS), percent));
    }

    @Override
    public float lootMulti() {
        return 2;
    }
}
