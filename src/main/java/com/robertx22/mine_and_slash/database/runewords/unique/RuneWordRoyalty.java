package com.robertx22.mine_and_slash.database.runewords.unique;

import com.robertx22.mine_and_slash.database.items.runes.GohItem;
import com.robertx22.mine_and_slash.database.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.database.items.runes.unique_runes.WORItem;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.LootTypeBonusFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class RuneWordRoyalty extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new LootTypeBonusFlat(LootType.Currency));
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public String GUID() {
        return "Royalty";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new WORItem(), new GohItem(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Royalty";
    }

}
