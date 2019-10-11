package com.robertx22.mine_and_slash.database.affixes.prefixes.resource;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.percent.EnergyRegenPercent;

import java.util.Arrays;
import java.util.List;

public class Energetic extends Prefix {

    public Energetic() {
        super(new Requirements(SlotRequirement.jewerlyOnly()));
    }

    @Override
    public String GUID() {
        return "Energetic";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new EnergyRegenPercent());
    }

    @Override
    public String locNameForLangFile() {
        return "Energetic";
    }
}
