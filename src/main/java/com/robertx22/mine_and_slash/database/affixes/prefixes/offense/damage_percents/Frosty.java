package com.robertx22.mine_and_slash.database.affixes.prefixes.offense.damage_percents;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class Frosty extends BaseDamagePercentPrefix {

    public Frosty() {
    }

    @Override
    public String GUID() {
        return "frosty";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Water));
    }

    @Override
    public String locNameForLangFile() {
        return "Frosty";
    }

}
