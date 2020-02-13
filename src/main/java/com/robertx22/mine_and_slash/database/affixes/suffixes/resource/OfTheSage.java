package com.robertx22.mine_and_slash.database.affixes.suffixes.resource;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ManaRegenPercent;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class OfTheSage extends Suffix {

    public OfTheSage() {
        super(new Requirements(SlotRequirement.jewerlyOnly()));
    }

    @Override
    public String GUID() {
        return "of_the_sage";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new ManaRegenPercent(), new ManaRegenFlat());
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public String locNameForLangFile() {
        return "Of The Sage";
    }
}
