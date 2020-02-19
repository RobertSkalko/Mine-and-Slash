package com.robertx22.mine_and_slash.database.runewords.slots_5;

import com.robertx22.mine_and_slash.database.runes.*;
import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.EnergyFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.EnergyRegenPercent;

import java.util.Arrays;
import java.util.List;

public class RuneWordInfinity extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new EnergyFlat(), new EnergyRegenFlat(), new EnergyRegenPercent());
    }

    @Override
    public String GUID() {
        return "infinity";
    }

    @Override
    public List<BaseRune> runes() {
        return Arrays.asList(new Cen(0), new Dos(0), new Ano(0), new Ber(0), new Xah(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Infinity";
    }
}
