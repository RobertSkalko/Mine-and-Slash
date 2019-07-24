package com.robertx22.mine_and_slash.database.unique_items.bracelets;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.less.LessCriticalDamagePercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueBracelet;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class BraceletFire extends BaseUniqueBracelet {

    public BraceletFire() {

    }

    @Override
    public int Tier() {
        return 3;
    }

    @Override
    public String GUID() {
        return "braceletfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Fire), new ElementalSpellToAttackDMGFlat(Elements.Fire), new ElementalResistFlat(Elements.Fire), new LessCriticalDamagePercent(), new CrippleLifeOnHitPercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Flaming Fists";
    }

    @Override
    public String locDescForLangFile() {
        return "Harness Fire, wield it as your weapon. Incinerate them.";
    }
}
