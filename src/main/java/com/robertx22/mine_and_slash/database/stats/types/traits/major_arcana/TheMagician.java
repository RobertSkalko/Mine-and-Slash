package com.robertx22.mine_and_slash.database.stats.types.traits.major_arcana;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.IntelligenceFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.multi.resources.ManaMulti;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class TheMagician extends BaseMajorArcana {

    public static final String GUID = "TheMagician";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new IntelligenceFlat(), new ManaMulti().multi(2), new ElementalSpellDamageFlat(Elements.Thunder));
    }

    @Override
    public String locNameForLangFile() {
        return "The Magician";
    }
}
