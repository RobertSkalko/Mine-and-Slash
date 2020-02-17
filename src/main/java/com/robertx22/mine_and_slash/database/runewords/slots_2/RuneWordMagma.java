package com.robertx22.mine_and_slash.database.runewords.slots_2;

import com.robertx22.mine_and_slash.database.runes.DosItem;
import com.robertx22.mine_and_slash.database.runes.VohItem;
import com.robertx22.mine_and_slash.database.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class RuneWordMagma extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new ElementalResistFlat(Elements.Fire), new ArmorFlat());
    }

    @Override
    public String GUID() {
        return "magma";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new DosItem(0), new VohItem(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Magma";
    }
}
