package com.robertx22.mine_and_slash.database.affixes.suffixes.mixed;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.MagicShieldPercent;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class OfTheFallen extends Suffix {

    public OfTheFallen() {
        super(new Requirements(SlotRequirement.armorsOnly(), LevelRequirement.fromMidLevel()));
    }

    @Override
    public String GUID() {
        return "of_the_fallen";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new HealthFlat().size(StatMod.Size.LESS), new MagicShieldPercent().size(StatMod.Size.HIGH));
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public String locNameForLangFile() {
        return "Of The Fallen";
    }

}