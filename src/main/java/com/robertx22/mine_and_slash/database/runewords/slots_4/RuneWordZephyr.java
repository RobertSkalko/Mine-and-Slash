package com.robertx22.mine_and_slash.database.runewords.slots_4;

import com.robertx22.mine_and_slash.database.items.runes.BerItem;
import com.robertx22.mine_and_slash.database.items.runes.CenItem;
import com.robertx22.mine_and_slash.database.items.runes.MosItem;
import com.robertx22.mine_and_slash.database.items.runes.XahItem;
import com.robertx22.mine_and_slash.database.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class RuneWordZephyr extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(
                new EnergyRegenFlat(), new ElementalResistFlat(Elements.Thunder).multi(2), new ArmorFlat());
    }

    @Override
    public String GUID() {
        return "zephyr";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new MosItem(0), new XahItem(0), new CenItem(0), new BerItem(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Zephyr";
    }
}
