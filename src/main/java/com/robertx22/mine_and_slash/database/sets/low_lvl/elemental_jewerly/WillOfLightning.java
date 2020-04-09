package com.robertx22.mine_and_slash.database.sets.low_lvl.elemental_jewerly;

import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.CriticalHitPercent;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.HashMap;

public class WillOfLightning extends Set {

    @Override
    public String locNameForLangFile() {
        return "Will of Lightning";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new CriticalHitPercent());
                    put(3, new ElementalSpellDamageFlat(Elements.Thunder));

                }
            }
        };
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.jewerlyOnly(), LevelRequirement.lowLVLOnly());
    }

    @Override
    public String GUID() {
        return "will_of_lightning";
    }
}
