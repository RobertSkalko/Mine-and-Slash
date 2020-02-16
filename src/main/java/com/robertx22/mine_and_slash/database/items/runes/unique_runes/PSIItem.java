package com.robertx22.mine_and_slash.database.items.runes.unique_runes;

import com.robertx22.mine_and_slash.database.items.runes.base.BaseUniqueRuneItem;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.HighCriticalDamageFlat;

import java.util.Arrays;
import java.util.List;

public class PSIItem extends BaseUniqueRuneItem {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new HighCriticalDamageFlat());
    }

    @Override
    public String name() {
        return "PSI";
    }

    @Override
    public int Tier() {
        return 2;
    }

}
