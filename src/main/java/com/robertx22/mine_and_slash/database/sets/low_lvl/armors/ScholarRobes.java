package com.robertx22.mine_and_slash.database.sets.low_lvl.armors;

import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.misc.BonusExpFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.LootTypeBonusFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;

import java.util.HashMap;

public class ScholarRobes extends Set {

    @Override
    public String locNameForLangFile() {
        return "Scholar's Robes";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new BonusExpFlat());
                    put(3, new LootTypeBonusFlat(LootType.NormalItem));
                    put(4, new LootTypeBonusFlat(LootType.Map));

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
        return "scholars_robes";
    }
}

