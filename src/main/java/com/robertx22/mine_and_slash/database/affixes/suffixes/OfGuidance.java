package com.robertx22.mine_and_slash.database.affixes.suffixes;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.gearitemslots.Helmet;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.misc.BonusExpFlat;

import java.util.Arrays;
import java.util.List;

public class OfGuidance extends Suffix {

    @Override
    public String GUID() {
        return "of_guidance";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new BonusExpFlat());
    }

    @Override
    public Requirements requirements() {
        return new Requirements(new SlotRequirement(new Helmet()));
    }

    @Override
    public String locNameForLangFile() {
        return "Of Guidance";
    }
}
