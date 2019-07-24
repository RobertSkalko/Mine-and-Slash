package com.robertx22.mine_and_slash.database.stats.stat_types.traits.major_arcana;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.corestats.WisdomFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.DodgePercent;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class Hermit extends BaseMajorArcana {

    public static final String GUID = "Hermit";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new WisdomFlat(), new DodgePercent(), new CriticalHitFlat(), new ElementalSpellDamageFlat(Elements.Nature));
    }

    @Override
    public String locNameForLangFile() {
        return "The Hermit";
    }
}
