package com.robertx22.mine_and_slash.database.unique_items.chest;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.MajorArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalTransferFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.less.LessDodgePercent;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueChest;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class ChestWater extends BaseUniqueChest {

    public ChestWater() {

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
        return Arrays.asList(new HealthFlat(), new ElementalPeneFlat(Elements.Water).multi(2), new MajorArmorFlat(), new ElementalTransferFlat(Elements.Nature, Elements.Water)
                .multi(2), new LessDodgePercent());
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