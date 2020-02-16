package com.robertx22.mine_and_slash.database.sets.from_lvl_50;

import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.BonusExpFlat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Wisdom;

import java.util.HashMap;

public class SeersGuidance extends Set {

    @Override
    public String locNameForLangFile() {
        return "Seer's Guidance";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new BonusExpFlat());
                    put(3, new CoreStatFlat(Wisdom.INSTANCE));

                }
            }
        };
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.helmetCharmAndNecklace(), LevelRequirement.fromLVL50());
    }

    @Override
    public String GUID() {
        return "seers_guidance";
    }
}
