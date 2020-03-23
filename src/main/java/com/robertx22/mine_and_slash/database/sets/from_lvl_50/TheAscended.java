package com.robertx22.mine_and_slash.database.sets.from_lvl_50;

import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Vitality;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.HashMap;

public class TheAscended extends Set {

    @Override
    public String locNameForLangFile() {
        return "The Ascended";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new ArmorFlat());
                    put(3, new CoreStatFlat(Vitality.INSTANCE).size(StatMod.Size.HIGH));
                    put(4, new HealthFlat());
                }
            }
        };
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.armorsOnlyNoOffHand(), LevelRequirement.fromHighLevel());
    }

    @Override
    public String GUID() {
        return "the_ascended";
    }
}
