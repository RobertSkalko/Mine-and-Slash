package com.robertx22.mine_and_slash.database.affixes.suffixes.defense;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.DodgeRatingFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.DodgePercent;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class OfHiddenSense extends Suffix {

    public OfHiddenSense() {
        super(new Requirements(SlotRequirement.armorsOnly()));
    }

    @Override
    public String GUID() {
        return "of_hidden_sense";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new DodgeRatingFlat(), new DodgePercent());

    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public String locNameForLangFile() {
        return "Of Hidden Sense";
    }
}