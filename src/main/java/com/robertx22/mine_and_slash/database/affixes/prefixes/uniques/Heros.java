package com.robertx22.mine_and_slash.database.affixes.prefixes.uniques;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.percent.BlockStrengthPercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.HealthPercent;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class Heros extends Prefix {

    public Heros() {
        super(new Requirements(SlotRequirement.shield()));
    }

    @Override
    public String GUID() {
        return "heros";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new BlockStrengthPercent(), new HealthPercent());
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public String locNameForLangFile() {
        return "Hero's";
    }
}

