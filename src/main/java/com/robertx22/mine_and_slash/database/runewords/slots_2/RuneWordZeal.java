package com.robertx22.mine_and_slash.database.runewords.slots_2;

import com.robertx22.mine_and_slash.database.items.runes.GohItem;
import com.robertx22.mine_and_slash.database.items.runes.MosItem;
import com.robertx22.mine_and_slash.database.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.EnergyRegenPercent;

import java.util.Arrays;
import java.util.List;

public class RuneWordZeal extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new EnergyRegenPercent(), new ArmorFlat());
    }

    @Override
    public String GUID() {
        return "zeal";
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
