package com.robertx22.mine_and_slash.database.stats.types.traits.major_arcana;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Intelligence;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Strength;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class Chariot extends BaseMajorArcana {

    public static final String GUID = "chariot";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new CoreStatFlat(Intelligence.INSTANCE), new CoreStatFlat(Strength.INSTANCE),
                             new ElementalSpellToAttackDMGFlat(Elements.Water)
        );
    }

    @Override
    public String locNameForLangFile() {
        return "Chariot";
    }

}
