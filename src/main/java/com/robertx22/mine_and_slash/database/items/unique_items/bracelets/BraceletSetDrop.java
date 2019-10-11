package com.robertx22.mine_and_slash.database.items.unique_items.bracelets;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueBracelet;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.SpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.LootTypeBonusFlat;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class BraceletSetDrop extends BaseUniqueBracelet {

    public BraceletSetDrop() {

    }

    static StatReq req = new StatReq(LvlPointStat.WISDOM, StatReq.Size.SMALL, LvlPointStat.STAMINA, StatReq.Size.MEDIUM);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 6;
    }

    @Override
    public boolean canGetSet() {
        return true;
    }

    @Override
    public String GUID() {
        return "braceletsetdrop0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new LootTypeBonusFlat(LootType.UniqueItem));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new SpellDamageFlat());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Gloves of Excavation";
    }

    @Override
    public String locDescForLangFile() {
        return "No matter the depths, it will be mine!";
    }
}
