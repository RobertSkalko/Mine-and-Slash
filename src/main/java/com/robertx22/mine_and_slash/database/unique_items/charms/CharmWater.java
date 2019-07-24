package com.robertx22.mine_and_slash.database.unique_items.charms;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.conversions.ManaToEnergyConvFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalPenePercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleDodgePercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueCharm;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class CharmWater extends BaseUniqueCharm {

    public CharmWater() {

    }

    @Override
    public int Tier() {
        return 18;
    }

    @Override
    public String GUID() {
        return "charmwater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ManaToEnergyConvFlat(), new ArmorFlat(), new ElementalPeneFlat(Elements.Water), new ElementalPenePercent(Elements.Water), new ElementalResistFlat(Elements.Water), new CrippleDodgePercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Charm of the River Dragon";
    }

    @Override
    public String locDescForLangFile() {
        return "My path cannot be stopped.";
    }
}
