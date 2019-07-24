package com.robertx22.mine_and_slash.database.runewords.slots_2;

import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.EnergyRegenPercent;
import com.robertx22.mine_and_slash.items.runes.GohItem;
import com.robertx22.mine_and_slash.items.runes.MosItem;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;

import java.util.Arrays;
import java.util.List;

public class RuneWordZeal extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new EnergyRegenPercent(), new ArmorFlat());
    }

    @Override
    public String GUID() {
        return "Zeal";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new GohItem(0), new MosItem(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Zeal";
    }
}
