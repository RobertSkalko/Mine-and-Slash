package com.robertx22.mine_and_slash.database.unique_items.charms;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalAffinityFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalPenePercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamagePercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.ManaRegenPercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueCharm;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class CharmThunder extends BaseUniqueCharm {

    public CharmThunder() {

    }

    @Override
    public int Tier() {
        return 19;
    }

    @Override
    public String GUID() {
        return "charmthunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalSpellDamagePercent(Elements.Thunder), new ManaRegenPercent(), new ElementalPenePercent(Elements.Thunder), new ElementalAffinityFlat(Elements.Thunder)
                .multi(0.8F), new CrippleLifeOnHitPercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Charm of Heavenly Tribulations";
    }

    @Override
    public String locDescForLangFile() {
        return "Jade is only worth after it is polished.";
    }
}
