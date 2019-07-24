package com.robertx22.mine_and_slash.database.unique_items.necklaces;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueNecklace;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class MagesLuckyAmulet extends BaseUniqueNecklace {

    @Override
    public int Tier() {
        return 18;
    }

    @Override
    public String GUID() {
        return "necklacegambler0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new HealthFlat(), new ElementalResistFlat(Elements.Elemental)
                .multi(0.5F), new ArmorFlat(), new ManaFlat().multi(2));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Mage's Lucky Amulet";
    }

    @Override
    public String locDescForLangFile() {
        return "One more bet!";
    }
}
