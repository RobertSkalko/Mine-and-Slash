package com.robertx22.mine_and_slash.database.runewords.slots_3;

import com.robertx22.mine_and_slash.database.runes.Ita;
import com.robertx22.mine_and_slash.database.runes.Mos;
import com.robertx22.mine_and_slash.database.runes.Rah;
import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ManaRegenPercent;

import java.util.Arrays;
import java.util.List;

public class RuneWordRadiance extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new ManaOnHitFlat(), new ManaRegenPercent());
    }

    @Override
    public String GUID() {
        return "radiance";
    }

    @Override
    public List<BaseRune> runes() {

        return Arrays.asList(new Ita(0), new Mos(0), new Rah(0));

    }

    @Override
    public String locNameForLangFile() {
        return "Radiance";
    }
}