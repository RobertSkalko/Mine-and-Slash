package com.robertx22.mine_and_slash.database.sets.endgame_lvl;

import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaFlat;

import java.util.HashMap;

public class ArchmageArmor extends Set {

    public static ArchmageArmor INSTANCE = new ArchmageArmor();

    @Override
    public String locNameForLangFile() {
        return "Archmage Armor";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new ManaFlat());
                    put(3, new MagicShieldRegenFlat());
                    put(4, new MagicShieldFlat());

                }
            }
        };
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.clothArmorOnly(), LevelRequirement.endgameLVLOnly());
    }

    @Override
    public String GUID() {
        return "archmage_armor";
    }

}
