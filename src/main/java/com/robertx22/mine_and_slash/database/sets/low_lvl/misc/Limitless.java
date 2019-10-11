package com.robertx22.mine_and_slash.database.sets.low_lvl.misc;

import com.robertx22.mine_and_slash.database.gearitemslots.curios.Charm;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Ring;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.percent.EnergyRegenPercent;

import java.util.HashMap;

public class Limitless extends Set {

    @Override
    public String locNameForLangFile() {
        return "Limitless";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new EnergyRegenPercent());

                }
            }
        };
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.slots(Ring.INSTANCE, Charm.INSTANCE), LevelRequirement
                .lowLVLOnly());
    }

    @Override
    public String GUID() {
        return "limitless";
    }

}