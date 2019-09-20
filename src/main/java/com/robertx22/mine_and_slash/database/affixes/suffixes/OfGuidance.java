package com.robertx22.mine_and_slash.database.affixes.suffixes;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlateHelmet;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.misc.BonusExpFlat;

import java.util.Arrays;
import java.util.List;

public class OfGuidance extends Suffix {

    public OfGuidance() {
        super(new Requirements(new SlotRequirement(new PlateHelmet())));
    }

    @Override
    public String GUID() {
        return "of_guidance";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new BonusExpFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "Of Guidance";
    }
}
