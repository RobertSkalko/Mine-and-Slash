package com.robertx22.mine_and_slash.database.runewords.unique;

import com.robertx22.mine_and_slash.database.runes.Goh;
import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.runes.unique_runes.WOR;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.LootTypeBonusFlat;
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
        return "royalty";
    }

    @Override
    public List<BaseRune> runes() {
        return Arrays.asList(new WOR(), new Goh(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Royalty";
    }

}
