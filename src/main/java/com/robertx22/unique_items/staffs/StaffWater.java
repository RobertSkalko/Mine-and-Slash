package com.robertx22.unique_items.staffs;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.gearitemslots.Staff;
import com.robertx22.database.stat_mods.flat.CriticalDamageFlat;
import com.robertx22.database.stat_mods.flat.CriticalHitFlat;
import com.robertx22.database.stat_mods.flat.elemental.attack_dmg.AttackWaterDamageFlat;
import com.robertx22.database.stat_mods.flat.elemental.pene.WaterPeneFlat;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueStaff;

public class StaffWater extends BaseUniqueStaff {

	public StaffWater() {

	}

	@Override
	public int Tier() {
		return 5;
	}

	@Override
	public String name() {
		return "Staff of Permafrost";
	}

	@Override
	public String GUID() {
		return "uniquestaffwater0";
	}

	@Override
	public List<StatMod> uniqueStats() {
		return Arrays.asList(new AttackWaterDamageFlat(), new CriticalDamageFlat(), new CriticalHitFlat(),
				new WaterPeneFlat());
	}

	@Override
	public String description() {
		return "What is frozen is as good as dead.";
	}

	@Override
	public String slot() {
		return new Staff().Name();
	}
}
