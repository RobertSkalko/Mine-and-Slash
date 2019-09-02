package com.robertx22.mine_and_slash.database.items.unique_items.helmet;

import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueHelmet;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.AllTraitMods;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.less.LessManaOnHitPercent;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.low_dodge.LowDodgeAddCritHit;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class HelmetNature extends BaseUniqueHelmet {

    public HelmetNature() {

    }

    @Override
    public int Tier() {
        return 19;
    }

    @Override
    public String GUID() {
        return "helmetnature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new AllTraitMods(new LowDodgeAddCritHit()), new HealthRegenFlat(), new HealthFlat(), new ElementalResistFlat(Elements.Nature), new LessManaOnHitPercent());
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Nature));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Leaf Covering";
    }

    @Override
    public String locDescForLangFile() {
        return "Nature comes to my aid.";
    }
}