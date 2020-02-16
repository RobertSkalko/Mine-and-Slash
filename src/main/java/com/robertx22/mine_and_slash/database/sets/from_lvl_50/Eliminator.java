package com.robertx22.mine_and_slash.database.sets.from_lvl_50;

import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.HighCoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Dexterity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.HashMap;

public class Eliminator extends Set {

    @Override
    public String locNameForLangFile() {
        return "Eliminator";
    }

    @Override
    public int getRarityRank() {
        return IRarity.Rare;
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(3, new CoreStatFlat(Dexterity.INSTANCE));
                    put(4, new HighCoreStatFlat(Dexterity.INSTANCE));
                }
            }
        };
    }

    @Override
    public Requirements requirements() {
        return new Requirements(LevelRequirement.fromLVL50(), SlotRequirement.armorsOnlyNoOffHand());
    }

    @Override
    public String GUID() {
        return "eliminator";
    }
}
