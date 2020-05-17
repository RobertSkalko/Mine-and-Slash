package com.robertx22.mine_and_slash.database.stats.types.traits.good;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class Elemental extends Trait implements IAffectsOtherStats {

    public static String GUID = "elemental";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public int percent() {
        return 33;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Elemental));

    }

    @Override
    public String locNameForLangFile() {
        return "Elemental";
    }
}
