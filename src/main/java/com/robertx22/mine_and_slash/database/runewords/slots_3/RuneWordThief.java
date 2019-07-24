package com.robertx22.mine_and_slash.database.runewords.slots_3;

import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.DodgeFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.items.runes.GohItem;
import com.robertx22.mine_and_slash.items.runes.MosItem;
import com.robertx22.mine_and_slash.items.runes.RahItem;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;

import java.util.Arrays;
import java.util.List;

public class RuneWordThief extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new DodgeFlat(), new CriticalDamageFlat());
    }

    @Override
    public String GUID() {
        return "Thief";
    }

    @Override
    public List<BaseRuneItem> runes() {

        return Arrays.asList(new RahItem(0), new MosItem(0), new GohItem(0));

    }

    @Override
    public String locNameForLangFile() {
        return "Thief";
    }
}
