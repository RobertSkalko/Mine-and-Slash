package com.robertx22.mine_and_slash.database.sets.from_lvl_50;

import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.MajorDodgeFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.WeaponDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.DodgePercent;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;

import java.util.HashMap;

public class RoyalThiefAdornments extends Set {

    @Override
    public String locNameForLangFile() {
        return "Royal Thief's Adornments";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new DodgePercent());
                    put(3, new MajorDodgeFlat());
                    put(4, new WeaponDamageFlat(WeaponTypes.Bow).multi(1.5F));

                }
            }
        };
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.armorsOnly(), LevelRequirement.fromLVL50());
    }

    @Override
    public String GUID() {
        return "royal_thief_adornments";
    }
}
