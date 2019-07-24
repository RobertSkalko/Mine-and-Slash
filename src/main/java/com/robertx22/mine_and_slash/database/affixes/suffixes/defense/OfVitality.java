package com.robertx22.mine_and_slash.database.affixes.suffixes.defense;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.HealthPercent;

import java.util.Arrays;
import java.util.List;

public class OfVitality extends Suffix {

    public OfVitality() {
    }

    @Override
    public String GUID() {
        return "Of Vitality";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new HealthPercent());

    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.armorsOnly());
    }

    @Override
    public String locNameForLangFile() {
        return "Of Vitality";
    }
}
