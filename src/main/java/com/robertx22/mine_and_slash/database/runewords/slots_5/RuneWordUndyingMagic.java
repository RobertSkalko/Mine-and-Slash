package com.robertx22.mine_and_slash.database.runewords.slots_5;

import com.robertx22.mine_and_slash.database.items.runes.AnoItem;
import com.robertx22.mine_and_slash.database.items.runes.CenItem;
import com.robertx22.mine_and_slash.database.items.runes.MosItem;
import com.robertx22.mine_and_slash.database.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.database.items.runes.unique_runes.PSIItem;
import com.robertx22.mine_and_slash.database.items.runes.unique_runes.QARItem;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.MagicShieldRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.MagicShieldPercent;

import java.util.Arrays;
import java.util.List;

public class RuneWordUndyingMagic extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new MagicShieldPercent().multi(3), new MagicShieldRegenFlat());
    }

    @Override
    public String GUID() {
        return "undying_magic";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new QARItem(), new AnoItem(0), new PSIItem(), new CenItem(0), new MosItem(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Undying Magic";
    }
}
