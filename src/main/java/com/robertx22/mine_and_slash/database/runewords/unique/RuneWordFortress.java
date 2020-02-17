package com.robertx22.mine_and_slash.database.runewords.unique;

import com.robertx22.mine_and_slash.database.runes.CenItem;
import com.robertx22.mine_and_slash.database.runes.MosItem;
import com.robertx22.mine_and_slash.database.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.database.runes.unique_runes.ONIItem;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.BlockStrengthFlat;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class RuneWordFortress extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new BlockStrengthFlat());
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public String GUID() {
        return "fortress";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new CenItem(0), new ONIItem(), new MosItem(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Fortress";
    }

}

