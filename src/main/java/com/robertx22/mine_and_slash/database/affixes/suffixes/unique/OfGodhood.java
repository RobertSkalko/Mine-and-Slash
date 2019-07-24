package com.robertx22.mine_and_slash.database.affixes.suffixes.unique;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.gearitemslots.Helmet;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.requirements.UniqueTierRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.MajorDodgeFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.multi.defense.HealthMulti;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class OfGodhood extends Suffix {

    @Override
    public String GUID() {
        return "of_godhood";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new HealthMulti(), new MajorDodgeFlat(), new ArmorFlat());

    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public Requirements requirements() {
        return new Requirements(new SlotRequirement(new Helmet()), LevelRequirement.fromLVL50(), new UniqueTierRequirement(10));
    }

    @Override
    public String locNameForLangFile() {
        return "Of Godhood";
    }
}
