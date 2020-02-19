package com.robertx22.mine_and_slash.database.runewords.slots_4;

import com.robertx22.mine_and_slash.database.runes.Ano;
import com.robertx22.mine_and_slash.database.runes.Cen;
import com.robertx22.mine_and_slash.database.runes.Dos;
import com.robertx22.mine_and_slash.database.runes.Rah;
import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaRegenFlat;

import java.util.Arrays;
import java.util.List;

public class RuneWordProfoundSea extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new ManaRegenFlat(), new ManaFlat().size(StatMod.Size.HIGH));
    }

    @Override
    public String GUID() {
        return "profound_sea";
    }

    @Override
    public List<BaseRune> runes() {
        return Arrays.asList(new Dos(0), new Ano(0), new Rah(0), new Cen(0));

    }

    @Override
    public String locNameForLangFile() {
        return "Profound Sea";
    }
}
