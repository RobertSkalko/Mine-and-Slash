package com.robertx22.mine_and_slash.database.affixes.prefixes.mixed;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.ReducedManaCostFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.HealthPercent;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class Forgotten extends Prefix {

    public Forgotten() {
        super(new Requirements(SlotRequirement.jewerlyOnly()));
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public String GUID() {
        return "forgotten";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(
            new HealthPercent().size(StatMod.Size.HALF_LESS),
            new ReducedManaCostFlat()
        );
    }

    @Override
    public String locNameForLangFile() {
        return "Forgotten";
    }
}
