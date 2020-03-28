package com.robertx22.mine_and_slash.database.affixes.prefixes.curses;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class Old extends Prefix {

    public Old() {
        super(new Requirements(SlotRequirement.jewerlyOnly()));
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public String GUID() {
        return "old";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(
            new EnergyRegenFlat().size(StatMod.Size.HALF_LESS)
        );
    }

    @Override
    public String locNameForLangFile() {
        return "Old";
    }
}

