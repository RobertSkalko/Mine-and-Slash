package com.robertx22.unique_items.chest;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stat_mods.flat.resources.ManaFlat;
import com.robertx22.database.stat_mods.flat.resources.conversions.EnergyToManaConvFlat;
import com.robertx22.database.stat_mods.flat.weapon_damages.StaffDamageFlat;
import com.robertx22.database.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueChest;

public class ChestMana extends BaseUniqueChest {

    public ChestMana() {

    }

    @Override
    public int Tier() {
	return 11;

    }

    @Override
    public String GUID() {
	return "chestmana0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new EnergyToManaConvFlat(), new HealthFlat(), new ManaFlat(), new EnergyRegenFlat(),
		new StaffDamageFlat(), new CrippleLifeOnHitPercent());
    }

}