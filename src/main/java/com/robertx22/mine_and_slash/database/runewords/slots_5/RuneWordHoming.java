package com.robertx22.mine_and_slash.database.runewords.slots_5;

import com.robertx22.mine_and_slash.database.runes.Dos;
import com.robertx22.mine_and_slash.database.runes.Ita;
import com.robertx22.mine_and_slash.database.runes.Voh;
import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.HealthPercent;

import java.util.Arrays;
import java.util.List;

public class RuneWordHoming extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new CriticalHitFlat(), new CriticalDamageFlat(), new HealthPercent());
    }

    @Override
    public String GUID() {
        return "follower";
    }

    @Override
    public List<BaseRune> runes() {
        return Arrays.asList(new Ita(0), new Dos(0), new Voh(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Follower";
    }
}
