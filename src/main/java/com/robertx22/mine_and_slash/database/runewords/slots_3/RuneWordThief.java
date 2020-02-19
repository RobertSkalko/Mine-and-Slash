package com.robertx22.mine_and_slash.database.runewords.slots_3;

import com.robertx22.mine_and_slash.database.runes.Goh;
import com.robertx22.mine_and_slash.database.runes.Mos;
import com.robertx22.mine_and_slash.database.runes.Rah;
import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.DodgeRatingFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;

import java.util.Arrays;
import java.util.List;

public class RuneWordThief extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new DodgeRatingFlat(), new CriticalDamageFlat());
    }

    @Override
    public String GUID() {
        return "thief";
    }

    @Override
    public List<BaseRune> runes() {

        return Arrays.asList(new Rah(0), new Mos(0), new Goh(0));

    }

    @Override
    public String locNameForLangFile() {
        return "Thief";
    }
}
