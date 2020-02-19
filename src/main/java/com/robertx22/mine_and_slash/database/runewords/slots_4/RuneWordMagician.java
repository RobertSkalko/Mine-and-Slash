package com.robertx22.mine_and_slash.database.runewords.slots_4;

import com.robertx22.mine_and_slash.database.runes.Ano;
import com.robertx22.mine_and_slash.database.runes.Dos;
import com.robertx22.mine_and_slash.database.runes.Rah;
import com.robertx22.mine_and_slash.database.runes.Xah;
import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
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
        return "magician";
    }

    @Override
    public List<BaseRune> runes() {
        return Arrays.asList(new Dos(0), new Ano(0), new Xah(0), new Rah(0));

    }

    @Override
    public String locNameForLangFile() {
        return "Magician";
    }
}
