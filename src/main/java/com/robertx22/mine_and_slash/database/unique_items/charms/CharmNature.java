package com.robertx22.mine_and_slash.database.unique_items.charms;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalPenePercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamagePercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.HealthRegenPercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.less.LessCriticalDamagePercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueCharm;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class CharmNature extends BaseUniqueCharm {

    public CharmNature() {

    }

    @Override
    public int Tier() {
        return 15;
    }

    @Override
    public String GUID() {
        return "charmnature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalSpellDamagePercent(Elements.Nature).multi(2), new HealthRegenPercent(), new ElementalPenePercent(Elements.Nature), new HealthFlat(), new ElementalResistFlat(Elements.Nature), new LessCriticalDamagePercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Spirit Spring Charm";
    }

    @Override
    public String locDescForLangFile() {
        return "My life may be limited, but I shine throughout it.";
    }
}
