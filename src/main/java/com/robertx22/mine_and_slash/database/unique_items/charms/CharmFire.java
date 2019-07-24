package com.robertx22.mine_and_slash.database.unique_items.charms;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.ManaRegenPercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleDodgePercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueCharm;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class CharmFire extends BaseUniqueCharm {

    public CharmFire() {

    }

    @Override
    public int Tier() {
        return 12;
    }

    @Override
    public String GUID() {
        return "charmfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Fire), new ManaRegenPercent(), new ElementalPeneFlat(Elements.Fire), new CriticalHitFlat(), new ElementalResistFlat(Elements.Fire), new CrippleDodgePercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Heavenly Fire Charm";
    }

    @Override
    public String locDescForLangFile() {
        return "Bath in flames, thrive!";
    }
}
