package com.robertx22.mine_and_slash.database.runewords.slots_4;

import com.robertx22.mine_and_slash.database.runes.Ber;
import com.robertx22.mine_and_slash.database.runes.Ita;
import com.robertx22.mine_and_slash.database.runes.Voh;
import com.robertx22.mine_and_slash.database.runes.Xah;
import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.DodgeRatingPercent;

import java.util.Arrays;
import java.util.List;

public class RuneWordLimitless extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new EnergyRegenFlat().size(StatMod.Size.HALF_MORE), new DodgeRatingPercent());
    }

    @Override
    public String GUID() {
        return "limitless";
    }

    @Override
    public List<BaseRune> runes() {
        return Arrays.asList(new Voh(0), new Ita(0), new Ber(0), new Xah(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Limitless";
    }
}
