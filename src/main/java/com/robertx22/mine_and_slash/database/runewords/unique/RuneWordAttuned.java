package com.robertx22.mine_and_slash.database.runewords.unique;

import com.robertx22.mine_and_slash.database.runes.BerItem;
import com.robertx22.mine_and_slash.database.runes.RahItem;
import com.robertx22.mine_and_slash.database.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.database.runes.unique_runes.QARItem;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class RuneWordAttuned extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new ElementalResistFlat(Elements.Elemental));
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public String GUID() {
        return "attuned";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new RahItem(0), new QARItem(), new BerItem(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Attuned";
    }

}
