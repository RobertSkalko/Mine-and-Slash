package com.robertx22.mine_and_slash.database.stats.stat_types.traits.major_arcana;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.multi.defense.CriticalHitMulti;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class WheelOfFortune extends BaseMajorArcana {

    public static final String GUID = "WheelOfFortune";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new CriticalHitFlat(), new CriticalDamageFlat(), new CriticalHitMulti(), new ElementalPeneFlat(Elements.Fire));
    }

    @Override
    public String locNameForLangFile() {
        return "Wheel of Fortune";
    }
}
