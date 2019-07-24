package com.robertx22.mine_and_slash.database.unique_items.helmet;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.corestats.WisdomFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaOnHitFlat;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueHelmet;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class HelmetWisdom extends BaseUniqueHelmet {

    public HelmetWisdom() {

    }

    @Override
    public boolean canGetSet() {
        return true;
    }

    @Override
    public int Tier() {
        return 12;
    }

    @Override
    public String GUID() {
        return "helmetwisdom0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new HealthFlat(), new WisdomFlat(), new ArmorFlat(), new ManaOnHitFlat()
                .multi(2));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Helmet of Wisdom";
    }

    @Override
    public String locDescForLangFile() {
        return "True wisdom is knowing you know nothing.";
    }

}
