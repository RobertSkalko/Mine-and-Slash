package com.robertx22.mine_and_slash.database.affixes.prefixes.defense;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.HighArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.HealthPercent;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class Gatekeepers extends Prefix {

    public Gatekeepers() {
        super(new Requirements(SlotRequirement.shield()));
    }

    @Override
    public String GUID() {
        return "gatekeepers";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new HighArmorFlat(), new HealthPercent());
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public String locNameForLangFile() {
        return "Gatekeeper's";
    }
}
