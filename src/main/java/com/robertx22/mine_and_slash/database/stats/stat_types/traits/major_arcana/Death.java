package com.robertx22.mine_and_slash.database.stats.stat_types.traits.major_arcana;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.LifeOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.AllElementalDamageMulti;
import com.robertx22.mine_and_slash.database.stats.stat_mods.map_mods.minus.LessHealthMap;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.offense.PhysicalDamagePercent;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class Death extends BaseMajorArcana {

    public static final String GUID = "Death";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new LessHealthMap(), new AllElementalDamageMulti(Elements.Water), new PhysicalDamagePercent(), new LifeOnHitFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "Death";
    }
}
