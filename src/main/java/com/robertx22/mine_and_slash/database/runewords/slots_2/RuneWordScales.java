package com.robertx22.mine_and_slash.database.runewords.slots_2;

import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.items.runes.BerItem;
import com.robertx22.mine_and_slash.items.runes.CenItem;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class RuneWordScales extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new ElementalResistFlat(Elements.Nature), new ArmorFlat());
    }

    @Override
    public String GUID() {
        return "Scales";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new BerItem(0), new CenItem(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Scales";
    }
}
