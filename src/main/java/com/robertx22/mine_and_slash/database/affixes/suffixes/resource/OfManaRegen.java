package com.robertx22.mine_and_slash.database.affixes.suffixes.resource;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.ManaRegenPercent;

import java.util.Arrays;
import java.util.List;

public class OfManaRegen extends Suffix {

    public OfManaRegen() {
    }

    @Override
    public String GUID() {
        return "Of Mana Regen";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new ManaRegenPercent());
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.jewerlyOnly());
    }

    @Override
    public String locNameForLangFile() {
        return "Of Mana Regen";
    }
}
