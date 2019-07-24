package com.robertx22.mine_and_slash.database.unique_items.rings;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalConversionFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamagePercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueRing;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class RingWaterFire extends BaseUniqueRing {

    public RingWaterFire() {

    }

    @Override
    public int Tier() {
        return 15;
    }

    @Override
    public String GUID() {
        return "ringwaterfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalSpellDamagePercent(Elements.Fire), new ElementalSpellDamagePercent(Elements.Water), new ElementalConversionFlat(Elements.Fire, Elements.Water), new ElementalConversionFlat(Elements.Water, Elements.Fire));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Frostfire Ring";
    }

    @Override
    public String locDescForLangFile() {
        return "I will attain perfect control.";
    }
}
