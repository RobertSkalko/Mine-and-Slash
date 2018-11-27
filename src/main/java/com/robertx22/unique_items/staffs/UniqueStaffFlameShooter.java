package com.robertx22.unique_items.staffs;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.gearitemslots.Staff;
import com.robertx22.database.stat_mods.flat.CriticalDamageFlat;
import com.robertx22.database.stat_mods.flat.elemental.attack_dmg.AttackFireDamageFlat;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueStaff;

public class UniqueStaffFlameShooter extends BaseUniqueStaff {

	public UniqueStaffFlameShooter() {

	}

	@Override
	public int Tier() {
		return 0;
	}

	@Override
	public String name() {
		return "Staff of Flames";
	}

	@Override
	public String GUID() {
		return "uniquestaff_flameshooter0";
	}

	@Override
	public List<StatMod> uniqueStats() {
		return Arrays.asList(new AttackFireDamageFlat(), new CriticalDamageFlat());
	}

	@Override
	public String description() {
		return "Leave only ashes behind.";
	}

	@Override
	public String slot() {
		return new Staff().Name();
	}
}
