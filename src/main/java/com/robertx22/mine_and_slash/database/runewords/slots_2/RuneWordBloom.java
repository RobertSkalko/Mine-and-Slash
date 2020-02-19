package com.robertx22.mine_and_slash.database.runewords.slots_2;

import com.robertx22.mine_and_slash.database.runes.Ano;
import com.robertx22.mine_and_slash.database.runes.Goh;
import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.percent.HealthPercent;

import java.util.Arrays;
import java.util.List;

public class RuneWordBloom extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new HealthPercent());
    }

    @Override
    public String GUID() {
        return "bloom";
    }

    @Override
    public List<BaseRune> runes() {
        return Arrays.asList(new Ano(0), new Goh(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Bloom";
    }

}
