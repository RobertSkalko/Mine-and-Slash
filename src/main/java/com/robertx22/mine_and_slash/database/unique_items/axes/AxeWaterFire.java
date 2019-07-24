package com.robertx22.mine_and_slash.database.unique_items.axes;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleCriticalDamagePercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleManaOnHitPercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueAxe;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class AxeWaterFire extends BaseUniqueAxe {
    public AxeWaterFire() {

    }

    @Override
    public int Tier() {
        return 16;
    }

    @Override
    public String GUID() {
        return "axewaterfire0";
    }

    float multi = 0.7F;

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalAttackDamageFlat(Elements.Fire).multi(multi), new ElementalAttackDamageFlat(Elements.Water)
                .multi(multi), new CrippleCriticalDamagePercent(), new CrippleLifeOnHitPercent(), new CrippleManaOnHitPercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Axe of Frostfire";
    }

    @Override
    public String locDescForLangFile() {
        return "My efforts to merge elements shall not be in vain.";
    }
}




