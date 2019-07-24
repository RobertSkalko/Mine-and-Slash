package com.robertx22.mine_and_slash.database.affixes.prefixes.offense.damage_percents;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamagePercent;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class Frosty extends BaseDamagePercentPrefix {

    public Frosty() {
    }

    @Override
    public String GUID() {
        return "Frosty";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new ElementalSpellDamagePercent(Elements.Water));
    }

    @Override
    public String locNameForLangFile() {
        return "Frosty";
    }
}
