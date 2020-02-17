package com.robertx22.mine_and_slash.database.runewords.slots_5;

import com.robertx22.mine_and_slash.database.runes.AnoItem;
import com.robertx22.mine_and_slash.database.runes.CenItem;
import com.robertx22.mine_and_slash.database.runes.MosItem;
import com.robertx22.mine_and_slash.database.runes.RahItem;
import com.robertx22.mine_and_slash.database.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.database.runes.unique_runes.PSIItem;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldRegenFlat;

import java.util.Arrays;
import java.util.List;

public class RuneWordUndyingMagic extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new MagicShieldFlat(), new MagicShieldRegenFlat());
    }

    @Override
    public String GUID() {
        return "undying_magic";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new RahItem(0), new AnoItem(0), new PSIItem(), new CenItem(0), new MosItem(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Undying Magic";
    }
}
