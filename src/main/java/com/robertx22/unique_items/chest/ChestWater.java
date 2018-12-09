package com.robertx22.unique_items.chest;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.MajorArmorFlat;
import com.robertx22.database.stat_mods.flat.elemental.pene.WaterPeneFlat;
import com.robertx22.database.stat_mods.flat.elemental.transfers.NatureToWaterTransferFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stat_mods.percent.ArmorPercent;
import com.robertx22.database.stat_mods.percent.less.LessDodgePercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueChest;

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
	return Arrays.asList(new HealthFlat(), new WaterPeneFlat(), new MajorArmorFlat(), new ArmorPercent(),
		new NatureToWaterTransferFlat(), new LessDodgePercent());
    }

}