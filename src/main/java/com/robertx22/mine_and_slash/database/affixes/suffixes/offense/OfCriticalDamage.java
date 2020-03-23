package com.robertx22.mine_and_slash.database.affixes.suffixes.offense;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;

import java.util.Arrays;
import java.util.List;

public class OfCriticalDamage extends Suffix {

    public OfCriticalDamage() {
        super(new Requirements(SlotRequirement.weaponsOnly(), LevelRequirement.fromLowLevel()));
    }

    @Override
    public String GUID() {
        return "of_critical_damage";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new CriticalDamageFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "Of Critical Damage";
    }

}
