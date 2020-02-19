package com.robertx22.mine_and_slash.database.runewords.slots_5;

import com.robertx22.mine_and_slash.database.runes.Ano;
import com.robertx22.mine_and_slash.database.runes.Cen;
import com.robertx22.mine_and_slash.database.runes.Mos;
import com.robertx22.mine_and_slash.database.runes.Rah;
import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.runes.unique_runes.PSI;
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
    public List<BaseRune> runes() {
        return Arrays.asList(new Rah(0), new Ano(0), new PSI(), new Cen(0), new Mos(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Undying Magic";
    }
}
