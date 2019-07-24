package com.robertx22.mine_and_slash.database.stats.stat_types.traits.major_arcana;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.corestats.DexterityFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.HealthPercent;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class TheFool extends BaseMajorArcana {

    public static final String GUID = "TheFool";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new DexterityFlat(), new CriticalHitFlat(), new HealthPercent(), new ElementalSpellDamageFlat(Elements.Thunder));
    }

    @Override
    public String locNameForLangFile() {
        return "The Fool";
    }
}
