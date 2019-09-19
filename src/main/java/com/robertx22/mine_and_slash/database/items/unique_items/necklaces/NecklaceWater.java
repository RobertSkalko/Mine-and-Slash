package com.robertx22.mine_and_slash.database.items.unique_items.necklaces;

import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueNecklace;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamagePercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.ArmorPercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleDodgePercent;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class NecklaceWater extends BaseUniqueNecklace {

    public NecklaceWater() {

    }

    @Override
    public int Tier() {
        return 7;
    }

    @Override
    public String GUID() {
        return "necklacewater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalSpellDamagePercent(Elements.Water), new ArmorPercent()
                .multi(2), new ElementalResistFlat(Elements.Water), new CrippleDodgePercent());
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Water));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Amulet of the Glacier";
    }

    @Override
    public String locDescForLangFile() {
        return "Crystal clear and yet so incredibly tough.";
    }
}
