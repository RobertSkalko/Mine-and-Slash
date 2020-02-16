package com.robertx22.mine_and_slash.database.stats.types.traits.major_arcana;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Vitality;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Wisdom;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class TheWorld extends BaseMajorArcana {

    public static final String GUID = "the_world";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new ElementalResistFlat(Elements.Nature), new CoreStatFlat(Vitality.INSTANCE),
                             new CoreStatFlat(Wisdom.INSTANCE)
        );
    }

    @Override
    public String locNameForLangFile() {
        return "The World";
    }
}
