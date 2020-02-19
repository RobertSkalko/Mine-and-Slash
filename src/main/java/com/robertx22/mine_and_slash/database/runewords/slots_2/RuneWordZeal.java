package com.robertx22.mine_and_slash.database.runewords.slots_2;

import com.robertx22.mine_and_slash.database.runes.Goh;
import com.robertx22.mine_and_slash.database.runes.Mos;
import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.EnergyRegenPercent;

import java.util.Arrays;
import java.util.List;

public class RuneWordZeal extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new EnergyRegenPercent(), new ArmorFlat());
    }

    @Override
    public String GUID() {
        return "zeal";
    }

    @Override
    public List<BaseRune> runes() {
        return Arrays.asList(new Goh(0), new Mos(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Zeal";
    }
}
