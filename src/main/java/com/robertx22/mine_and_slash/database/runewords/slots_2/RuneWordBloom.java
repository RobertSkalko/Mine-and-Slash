package com.robertx22.mine_and_slash.database.runewords.slots_2;

import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.HealthPercent;
import com.robertx22.mine_and_slash.items.runes.AnoItem;
import com.robertx22.mine_and_slash.items.runes.GohItem;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;

import java.util.Arrays;
import java.util.List;

public class RuneWordBloom extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new HealthPercent());
    }

    @Override
    public String GUID() {
        return "Bloom";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new AnoItem(0), new GohItem(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Bloom";
    }

}
