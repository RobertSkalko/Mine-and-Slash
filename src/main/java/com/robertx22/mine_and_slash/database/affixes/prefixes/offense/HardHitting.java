package com.robertx22.mine_and_slash.database.affixes.prefixes.offense;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;

import java.util.Arrays;
import java.util.List;

public class HardHitting extends Prefix {

    public HardHitting() {
        super(new Requirements(SlotRequirement.weaponsOnly()));
    }

    @Override
    public String GUID() {
        return "Hard Hitting";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new CriticalDamageFlat());

    }

    @Override
    public String locNameForLangFile() {
        return "Hard hitting";
    }
}
