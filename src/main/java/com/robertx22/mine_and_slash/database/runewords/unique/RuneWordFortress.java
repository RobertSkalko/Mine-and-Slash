package com.robertx22.mine_and_slash.database.runewords.unique;

import com.robertx22.mine_and_slash.database.runes.Cen;
import com.robertx22.mine_and_slash.database.runes.Mos;
import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.runes.unique_runes.ONI;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.BlockStrengthFlat;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class RuneWordFortress extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new BlockStrengthFlat());
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public String GUID() {
        return "fortress";
    }

    @Override
    public List<BaseRune> runes() {
        return Arrays.asList(new Cen(0), new ONI(), new Mos(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Fortress";
    }

}

