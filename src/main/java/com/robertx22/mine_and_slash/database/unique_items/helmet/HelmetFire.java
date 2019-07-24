package com.robertx22.mine_and_slash.database.unique_items.helmet;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.AllTraitMods;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.MajorArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.less.LessCriticalHitPercent;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.low_dodge.LowDodgeAddArmor;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueHelmet;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class HelmetFire extends BaseUniqueHelmet {

    public HelmetFire() {

    }

    @Override
    public int Tier() {
        return 17;
    }

    @Override
    public String GUID() {
        return "helmetfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new AllTraitMods(new LowDodgeAddArmor()), new ElementalSpellDamageFlat(Elements.Fire), new EnergyRegenFlat(), new MajorArmorFlat(), new ElementalResistFlat(Elements.Fire), new LessCriticalHitPercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Flame Atronach Helmet";
    }

    @Override
    public String locDescForLangFile() {
        return "I see flames all around me.";
    }
}