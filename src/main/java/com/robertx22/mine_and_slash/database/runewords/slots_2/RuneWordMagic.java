package com.robertx22.mine_and_slash.database.runewords.slots_2;

import com.robertx22.mine_and_slash.database.runes.Ano;
import com.robertx22.mine_and_slash.database.runes.Mos;
import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.percent.MagicShieldPercent;

import java.util.Arrays;
import java.util.List;

public class RuneWordMagic extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new MagicShieldPercent());
    }

    @Override
    public String GUID() {
        return "magic";
    }

    @Override
    public List<BaseRune> runes() {
        return Arrays.asList(new Ano(0), new Mos(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Magic";
    }
}
