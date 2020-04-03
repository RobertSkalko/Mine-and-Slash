package com.robertx22.mine_and_slash.database.affixes.suffixes.thon_reward;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamagePercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.HealthRegenPercent;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class OfGrowth extends Suffix {

    public OfGrowth() {
        super(new Requirements(SlotRequirement.armorsOnly(), LevelRequirement.fromMidLevel()));
    }

    @Override
    public String GUID() {
        return "of_growth";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(
            new HealthRegenPercent(),
            new ElementalSpellDamagePercent(Elements.Nature)
        );
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public String locNameForLangFile() {
        return "Of Growth";
    }
}