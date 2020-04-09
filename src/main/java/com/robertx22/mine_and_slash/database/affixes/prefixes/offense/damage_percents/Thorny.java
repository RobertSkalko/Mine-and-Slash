package com.robertx22.mine_and_slash.database.affixes.prefixes.offense.damage_percents;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class Thorny extends BaseDamagePercentPrefix {

    public Thorny() {
    }

    @Override
    public String GUID() {
        return "thorny";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Nature));
    }

    @Override
    public String locNameForLangFile() {
        return "Thorny";
    }
}
