package com.robertx22.mine_and_slash.database.stats.types.traits.major_arcana;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.HighArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Vitality;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class Tower extends BaseMajorArcana {

    public static final String GUID = "tower";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(
                new CoreStatFlat(Vitality.INSTANCE), new HighArmorFlat(), new ElementalResistFlat(Elements.Fire));
    }

    @Override
    public String locNameForLangFile() {
        return "Tower";
    }
}
