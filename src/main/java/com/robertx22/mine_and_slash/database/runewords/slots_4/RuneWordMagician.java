package com.robertx22.mine_and_slash.database.runewords.slots_4;

import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.items.runes.AnoItem;
import com.robertx22.mine_and_slash.items.runes.DosItem;
import com.robertx22.mine_and_slash.items.runes.RahItem;
import com.robertx22.mine_and_slash.items.runes.XahItem;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class RuneWordMagician extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new ManaFlat(), new ManaRegenFlat(), new ElementalResistFlat(Elements.Thunder));
    }

    @Override
    public String GUID() {
        return "Magician";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new DosItem(0), new AnoItem(0), new XahItem(0), new RahItem(0));

    }

    @Override
    public String locNameForLangFile() {
        return "Magician";
    }
}
