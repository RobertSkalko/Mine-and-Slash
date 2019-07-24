package com.robertx22.mine_and_slash.database.sets.mid_lvl;

import com.robertx22.mine_and_slash.database.gearitemslots.Chest;
import com.robertx22.mine_and_slash.database.gearitemslots.Shield;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.BlockStrengthPercent;

import java.util.Arrays;
import java.util.HashMap;

public class TheProtector extends Set {

    @Override
    public String locNameForLangFile() {
        return "The Protector";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new BlockStrengthPercent());

                }
            }
        };
    }

    @Override
    public Requirements requirements() {
        return new Requirements(new SlotRequirement(Arrays.asList(new Shield(), new Chest())), LevelRequirement
                .midLVLOnly());
    }

    @Override
    public String GUID() {
        return "the_protector";
    }
}
