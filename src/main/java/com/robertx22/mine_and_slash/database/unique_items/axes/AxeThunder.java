package com.robertx22.mine_and_slash.database.unique_items.axes;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.offense.CriticalDamagePercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.offense.CriticalHitPercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueAxe;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class AxeThunder extends BaseUniqueAxe {
    public AxeThunder() {

    }

    @Override
    public int Tier() {
        return 11;
    }

    @Override
    public String GUID() {
        return "axethunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalAttackDamageFlat(Elements.Thunder), new CriticalHitPercent()
                .multi(2.5F), new CriticalDamagePercent().multi(2.5F), new CrippleLifeOnHitPercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Thunderstorm Axe";
    }

    @Override
    public String locDescForLangFile() {
        return "Sparks fly, heads roll.";
    }
}
