package com.robertx22.mine_and_slash.database.unique_items.axes;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.offense.CriticalDamagePercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.offense.CriticalHitPercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueAxe;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class AxeFire extends BaseUniqueAxe {
    public AxeFire() {

    }

    @Override
    public int Tier() {
        return 3;
    }

    @Override
    public String GUID() {
        return "axefire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalAttackDamageFlat(Elements.Fire), new CriticalHitPercent()
                .multi(2), new CriticalDamagePercent(), new ElementalPeneFlat(Elements.Fire));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Axe of Blazing Cuts";
    }

    @Override
    public String locDescForLangFile() {
        return "Behold my dance of fire!";
    }
}
