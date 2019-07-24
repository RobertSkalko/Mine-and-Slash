package com.robertx22.mine_and_slash.database.sets.low_lvl.armors;

import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.ManaRegenPercent;

import java.util.HashMap;

public class MagesRobes extends Set {

    @Override
    public String locNameForLangFile() {
        return "Mage's Robes";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {

                    put(2, new ManaRegenPercent());

                }
            }
        };
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.armorsOnlyNoOffHand(), LevelRequirement.lowLVLOnly());
    }

    @Override
    public String GUID() {
        return "archemage_vestments";
    }
}