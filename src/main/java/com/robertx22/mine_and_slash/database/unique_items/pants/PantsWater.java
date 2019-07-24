package com.robertx22.mine_and_slash.database.unique_items.pants;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.MajorArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamagePercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalTransferFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.less.LessDodgePercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniquePants;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class PantsWater extends BaseUniquePants {

    public PantsWater() {

    }

    @Override
    public int Tier() {
        return 13;
    }

    @Override
    public String GUID() {
        return "pantswater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new HealthFlat(), new MajorArmorFlat(), new ElementalResistFlat(Elements.Water)
                .multi(2), new ElementalTransferFlat(Elements.Fire, Elements.Water), new ElementalSpellDamagePercent(Elements.Water), new LessDodgePercent());

    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Leggings of the Glacier";
    }

    @Override
    public String locDescForLangFile() {
        return "Try to move me, I dare you.";
    }
}
