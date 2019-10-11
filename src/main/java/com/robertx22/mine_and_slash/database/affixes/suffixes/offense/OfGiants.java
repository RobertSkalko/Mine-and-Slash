package com.robertx22.mine_and_slash.database.affixes.suffixes.offense;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.HealthPercent;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class OfGiants extends Suffix {

    public OfGiants() {
        super(new Requirements(SlotRequirement.armorsOnly(), LevelRequirement.fromLVL10()));
    }

    @Override
    public String GUID() {
        return "of_giants";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new CriticalDamageFlat(), new HealthPercent());

    }

    @Override
    public int getRarityRank() {
        return IRarity.Rare;
    }

    @Override
    public String locNameForLangFile() {
        return "Of Giants";
    }

}

