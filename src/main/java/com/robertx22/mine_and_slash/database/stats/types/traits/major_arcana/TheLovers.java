package com.robertx22.mine_and_slash.database.stats.types.traits.major_arcana;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldRegenFlat;

import java.util.Arrays;
import java.util.List;

public class TheLovers extends BaseMajorArcana {

    public static final String GUID = "the_lovers";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(
            new HealthRegenFlat(), new MagicShieldRegenFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "The Lovers";
    }

}
