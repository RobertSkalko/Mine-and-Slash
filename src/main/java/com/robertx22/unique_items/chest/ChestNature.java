package com.robertx22.unique_items.chest;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stat_mods.flat.elemental.transfers.WaterToNatureTransferFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stat_mods.percent.HealthPercent;
import com.robertx22.database.stat_mods.percent.much_less.CrippleDodgePercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueChest;

public class ChestNature extends BaseUniqueChest {

    public ChestNature() {

    }

    @Override
    public int Tier() {
	return 11;

    }

    @Override
    public String name() {
	return "Wooden Plate";
    }

    @Override
    public String GUID() {
	return "chestnature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new HealthFlat(), new HealthPercent(), new HealthRegenFlat(), new ArmorFlat(),
		new WaterToNatureTransferFlat(), new CrippleDodgePercent());
    }

    @Override
    public String description() {
	return "Do not try move me.";
    }

}