package com.robertx22.mine_and_slash.database.unique_items.swords;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalTransferFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleLifestealPercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueSword;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class SwordWater extends BaseUniqueSword {
    public SwordWater() {

    }

    @Override
    public int Tier() {
        return 6;
    }

    @Override
    public String GUID() {
        return "swordwater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalAttackDamageFlat(Elements.Water), new ElementalTransferFlat(Elements.Thunder, Elements.Water), new ManaRegenFlat(), new ManaOnHitFlat(), new EnergyRegenFlat(), new CrippleLifestealPercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Ice Elemental Sword";
    }

    @Override
    public String locDescForLangFile() {
        return "Energy is everywhere, it just begs to be grasped";
    }
}
