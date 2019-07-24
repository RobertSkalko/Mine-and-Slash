package com.robertx22.mine_and_slash.database.unique_items.helmet;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.AllTraitMods;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalAffinityFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.less.LessLifeOnHitPercent;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.low_dodge.LowDodgeAddCritHit;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueHelmet;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class HelmetThunder extends BaseUniqueHelmet {

    public HelmetThunder() {

    }

    @Override
    public int Tier() {
        return 18;
    }

    @Override
    public String GUID() {
        return "helmetthunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new AllTraitMods(new LowDodgeAddCritHit()), new ElementalSpellToAttackDMGFlat(Elements.Thunder), new EnergyRegenFlat(), new ElementalAffinityFlat(Elements.Thunder), new ArmorFlat(), new LessLifeOnHitPercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Thunder Atronach Helmet";
    }

    @Override
    public String locDescForLangFile() {
        return "I see sparks all around me.";
    }
}