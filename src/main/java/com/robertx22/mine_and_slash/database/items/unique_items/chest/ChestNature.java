package com.robertx22.mine_and_slash.database.items.unique_items.chest;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueChest;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalTransferFlat;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class ChestNature extends BaseUniqueChest {

    public ChestNature() {

    }

    static StatReq req = new StatReq(LvlPointStat.VITALITY, StatReq.Size.BIG);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 11;

    }

    @Override
    public String GUID() {
        return "chestnature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new HealthRegenFlat().multi(2), new ElementalResistFlat(Elements.Nature), new ElementalTransferFlat(Elements.Water, Elements.Nature)
                .multi(2));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new HealthFlat());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Wooden Plate";
    }

    @Override
    public String locDescForLangFile() {
        return "Do not try move me.";
    }
}