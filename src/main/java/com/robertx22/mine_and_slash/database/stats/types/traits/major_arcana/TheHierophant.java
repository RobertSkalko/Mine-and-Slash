package com.robertx22.mine_and_slash.database.stats.types.traits.major_arcana;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.mods.multi.resources.ManaMulti;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Wisdom;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class TheHierophant extends BaseMajorArcana {

    public static final String GUID = "the_hierophant";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(
                new CoreStatFlat(Wisdom.INSTANCE), new ManaMulti(), new ElementalPeneFlat(Elements.Nature));
    }

    @Override
    public String locNameForLangFile() {
        return "The Hierophant";
    }

}
