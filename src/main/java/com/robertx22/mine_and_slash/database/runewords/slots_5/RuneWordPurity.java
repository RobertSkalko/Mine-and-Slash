package com.robertx22.mine_and_slash.database.runewords.slots_5;

import com.robertx22.mine_and_slash.database.runes.*;
import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaRegenFlat;

import java.util.Arrays;
import java.util.List;

public class RuneWordPurity extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new HealthRegenFlat(), new HealthFlat(), new ManaRegenFlat());
    }

    @Override
    public String GUID() {
        return "purity";
    }

    @Override
    public List<BaseRune> runes() {
        return Arrays.asList(new Goh(0), new Ano(0), new Dos(0), new Cen(0), new Mos(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Purity";
    }
}
