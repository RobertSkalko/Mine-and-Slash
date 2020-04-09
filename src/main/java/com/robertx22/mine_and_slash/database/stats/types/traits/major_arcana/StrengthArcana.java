package com.robertx22.mine_and_slash.database.stats.types.traits.major_arcana;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Stamina;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class StrengthArcana extends BaseMajorArcana {

    public static final String GUID = "strength_arcana";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(
            new CoreStatFlat(Stamina.INSTANCE), new EnergyRegenFlat(),
            new ElementalSpellDamageFlat(Elements.Fire)
        );
    }

    @Override
    public String locNameForLangFile() {
        return "Strength";
    }

}
