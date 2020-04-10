package com.robertx22.mine_and_slash.database.affixes.suffixes.mixed;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.percent.MagicShieldPercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.PhysicalDamagePercent;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class OfMaliciousIntent extends Suffix {

    public OfMaliciousIntent() {
        super(new Requirements(SlotRequirement.ring(), LevelRequirement.fromMidLevel()));
    }

    @Override
    public String GUID() {
        return "of_malicious_intent";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(
            new MagicShieldPercent().size(StatMod.Size.ONE_LESS),
            new PhysicalDamagePercent()
        );
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public String locNameForLangFile() {
        return "Of Malicious Intent";
    }

}