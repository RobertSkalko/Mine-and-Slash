package com.robertx22.unique_items.staffs;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.PhysicalDamageFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stat_mods.flat.resources.LifeOnHitFlat;
import com.robertx22.database.stat_mods.flat.resources.LifestealFlat;
import com.robertx22.database.stat_mods.percent.LifestealPercent;
import com.robertx22.database.stat_mods.percent.much_less.CrippleManaOnHitPercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueStaff;

public class StaffLifesteal extends BaseUniqueStaff {

    public StaffLifesteal() {

    }

    @Override
    public int Tier() {
	return 10;
    }

    @Override
    public String name() {
	return "Vampire Staff";
    }

    @Override
    public String GUID() {
	return "uniquestafflifesteal0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new PhysicalDamageFlat(), new LifestealPercent(), new LifestealFlat(), new LifeOnHitFlat(),
		new HealthRegenFlat(), new CrippleManaOnHitPercent());
    }

    @Override
    public String description() {
	return "All blood will be mine, eventually.";
    }

}