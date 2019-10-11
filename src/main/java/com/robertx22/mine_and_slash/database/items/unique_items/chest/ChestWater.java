package com.robertx22.mine_and_slash.database.items.unique_items.chest;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueChest;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.MajorArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalTransferFlat;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class ChestWater extends BaseUniqueChest {

    public ChestWater() {

    }

    static StatReq req = new StatReq(LvlPointStat.INTELLIGENCE, StatReq.Size.NORMAL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 7;
    }

    @Override
    public String GUID() {
        return "chestwater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalPeneFlat(Elements.Water).multi(2), new MajorArmorFlat(), new ElementalTransferFlat(Elements.Nature, Elements.Water)
                .multi(2));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new HealthFlat());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Armor of the Glacier";
    }

    @Override
    public String locDescForLangFile() {
        return "I am a Fortress of pure Ice.";
    }
}