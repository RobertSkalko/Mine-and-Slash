package com.robertx22.mine_and_slash.database.sets.mid_lvl;

import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.elemental.AllEleDmgFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.elemental.AllEleSpellDmgFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaFlat;

import java.util.HashMap;

public class ArmorOfTheElements extends Set {

    @Override
    public String locNameForLangFile() {
        return "Armor of the Elements";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new ManaFlat());
                    put(3, new AllEleSpellDmgFlat());
                    put(4, new AllEleDmgFlat().multi(2));

                }
            }
        };
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.armorsOnlyNoOffHand(), LevelRequirement.midLVLOnly());
    }

    @Override
    public String GUID() {
        return "armor_of_the_elements";
    }
}
