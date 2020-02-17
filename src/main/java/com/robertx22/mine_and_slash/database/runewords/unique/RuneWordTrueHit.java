package com.robertx22.mine_and_slash.database.runewords.unique;

import com.robertx22.mine_and_slash.database.runes.AnoItem;
import com.robertx22.mine_and_slash.database.runes.VohItem;
import com.robertx22.mine_and_slash.database.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.database.runes.unique_runes.PSIItem;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.multi.defense.CriticalHitMulti;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.CriticalHitPercent;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class RuneWordTrueHit extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new CriticalHitPercent(), new CriticalHitMulti());
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public String GUID() {
        return "true_hit";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new AnoItem(0), new PSIItem(), new VohItem(0));
    }

    @Override
    public String locNameForLangFile() {
        return "True Hit";
    }

}
