package com.robertx22.mine_and_slash.database.items.runes.unique_runes;

import com.robertx22.mine_and_slash.database.items.runes.base.BaseUniqueRuneItem;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ArmorPercent;

import java.util.Arrays;
import java.util.List;

public class ONIItem extends BaseUniqueRuneItem {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new ArmorPercent().multi(2));
    }

    @Override
    public String name() {
        return "ONI";
    }

    @Override
    public int Tier() {
        return 4;
    }

}
