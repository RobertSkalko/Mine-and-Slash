package com.robertx22.mine_and_slash.database.unique_items.bows;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.corestats.WisdomFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueBow;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class BowWater extends BaseUniqueBow {
    @Override
    public List<StatMod> uniqueStats() {

        return Arrays.asList(new ElementalAttackDamageFlat(Elements.Water), new WisdomFlat(), new ElementalSpellToAttackDMGFlat(Elements.Water), new ElementalPeneFlat(Elements.Water));
    }

    @Override
    public String GUID() {
        return "bow_water0";
    }

    @Override
    public int Tier() {
        return 10;
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Bow of Water Affinity";
    }

    @Override
    public String locDescForLangFile() {
        return "Aim steady, imbue with Frost!";
    }
}
