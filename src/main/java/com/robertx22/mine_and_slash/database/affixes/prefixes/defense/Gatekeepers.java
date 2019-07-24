package com.robertx22.mine_and_slash.database.affixes.prefixes.defense;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.gearitemslots.Shield;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.MajorArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.HealthPercent;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class Gatekeepers extends Prefix {

    @Override
    public String GUID() {
        return "gatekeepers";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new MajorArmorFlat(), new HealthPercent());
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public Requirements requirements() {
        return new Requirements(new SlotRequirement(new Shield()));
    }

    @Override
    public String locNameForLangFile() {
        return "Gatekeeper's";
    }
}
