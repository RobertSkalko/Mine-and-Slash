package com.robertx22.mine_and_slash.database.runewords.slots_2;

import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.ArmorPercent;
import com.robertx22.mine_and_slash.items.runes.AnoItem;
import com.robertx22.mine_and_slash.items.runes.ItaItem;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;

import java.util.Arrays;
import java.util.List;

public class RuneWordStone extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new ArmorPercent());
    }

    @Override
    public String GUID() {
        return "Stone";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new ItaItem(0), new AnoItem(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Stone";
    }
}
