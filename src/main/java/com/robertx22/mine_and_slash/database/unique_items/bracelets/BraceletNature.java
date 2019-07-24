package com.robertx22.mine_and_slash.database.unique_items.bracelets;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.HealthRegenPercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleCriticalDamagePercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueBracelet;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class BraceletNature extends BaseUniqueBracelet {

    public BraceletNature() {

    }

    @Override
    public int Tier() {
        return 2;
    }

    @Override
    public String GUID() {
        return "braceletnature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Nature), new ElementalResistFlat(Elements.Nature), new HealthFlat(), new HealthRegenPercent(), new CrippleCriticalDamagePercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Thorn Bracers";
    }

    @Override
    public String locDescForLangFile() {
        return "Fools fight for treasure, but I keep on living.";
    }
}
