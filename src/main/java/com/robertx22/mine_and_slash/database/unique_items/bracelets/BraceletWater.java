package com.robertx22.mine_and_slash.database.unique_items.bracelets;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Bracelet;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class BraceletWater implements IUnique {

    public BraceletWater() {

    }

    static StatReq req = new StatReq(
        LvlPointStat.WISDOM, StatReq.Size.MEDIUM, LvlPointStat.VITALITY, StatReq.Size.SMALL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 8;
    }

    @Override
    public String GUID() {
        return "braceletwater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(
            new ElementalPeneFlat(Elements.Water),
            new ElementalPeneFlat(Elements.Fire)
        );
    }

    @Override
    public GearItemSlot getGearSlot() {
        return Bracelet.INSTANCE;
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(
            new ElementalSpellDamageFlat(Elements.Water).size(StatMod.Size.VERY_LOW),
            new ElementalSpellDamageFlat(Elements.Fire).size(StatMod.Size.VERY_LOW)
        );
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Frostburn Bracers";
    }

    @Override
    public String locDescForLangFile() {
        return "Burn them all! With Ice of course.";
    }
}
