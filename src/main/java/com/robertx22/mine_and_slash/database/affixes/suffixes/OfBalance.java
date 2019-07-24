package com.robertx22.mine_and_slash.database.affixes.suffixes;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.EnergyFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaFlat;

import java.util.Arrays;
import java.util.List;

public class OfBalance extends Suffix {

    @Override
    public String GUID() {
        return "of_balance";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new ManaFlat(), new EnergyFlat());
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.jewerlyOnly(), LevelRequirement.fromLVL20());
    }

    @Override
    public String locNameForLangFile() {
        return "Of Balance";
    }
}
