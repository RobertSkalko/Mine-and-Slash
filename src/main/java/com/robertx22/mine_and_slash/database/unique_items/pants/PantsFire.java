package com.robertx22.mine_and_slash.database.unique_items.pants;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.AllTraitMods;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalAffinityFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalTransferFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.low_dodge.LowDodgeAddCritHit;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniquePants;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class PantsFire extends BaseUniquePants {

    public PantsFire() {

    }

    @Override
    public int Tier() {
        return 15;
    }

    @Override
    public String GUID() {
        return "pantsfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new AllTraitMods(new LowDodgeAddCritHit()), new HealthFlat(), new ElementalAffinityFlat(Elements.Fire), new ArmorFlat(), new ElementalTransferFlat(Elements.Nature, Elements.Fire), new CrippleLifeOnHitPercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Flaming Leggings";
    }

    @Override
    public String locDescForLangFile() {
        return "Embrace my flames.";
    }
}
