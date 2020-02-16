package com.robertx22.mine_and_slash.database.items.unique_items.pants;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniquePantsItem;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.HighArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamagePercent;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalTransferFlat;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class PantsWater extends BaseUniquePantsItem {

    public PantsWater() {

    }

    static StatReq req = new StatReq(
            LvlPointStat.STAMINA, StatReq.Size.MEDIUM, LvlPointStat.INTELLIGENCE, StatReq.Size.SMALL);

    @Override
    public StatReq getRequirements() {
        return req;
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
        return Arrays.asList(new HighArmorFlat(), new ElementalResistFlat(Elements.Water).multi(2),
                             new ElementalTransferFlat(Elements.Fire, Elements.Water),
                             new ElementalSpellDamagePercent(Elements.Water)
        );

    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new HealthFlat());
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
