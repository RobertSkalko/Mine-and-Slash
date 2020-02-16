package com.robertx22.mine_and_slash.database.affixes.suffixes.resource;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.EnergyFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.HealthPercent;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class OfTheDepths extends Suffix {

    public OfTheDepths() {
        super(new Requirements(SlotRequirement.necklace()));
    }

    @Override
    public String GUID() {
        return "of_the_depths";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new ManaFlat(), new EnergyFlat(), new HealthPercent());
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public String locNameForLangFile() {
        return "Of The Depths";
    }
}