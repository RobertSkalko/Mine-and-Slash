package com.robertx22.mine_and_slash.database.affixes.prefixes.offense.damage_percents;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class Flaming extends BaseDamagePercentPrefix {

    public Flaming() {
    }

    @Override
    public String GUID() {
        return "flaming";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Fire));
    }

    @Override
    public String locNameForLangFile() {
        return "Flaming";
    }

}
