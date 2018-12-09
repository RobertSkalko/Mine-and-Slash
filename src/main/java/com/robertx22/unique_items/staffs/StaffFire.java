package com.robertx22.unique_items.staffs;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.CriticalDamageFlat;
import com.robertx22.database.stat_mods.flat.elemental.attack_dmg.AttackFireDamageFlat;
import com.robertx22.database.stat_mods.flat.elemental.pene.FirePeneFlat;
import com.robertx22.database.stat_mods.flat.resources.LifeOnHitFlat;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueStaff;

public class StaffFire extends BaseUniqueStaff {

    public StaffFire() {

    }

    @Override
    public int Tier() {
	return 5;
    }

    @Override
    public String GUID() {
	return "uniquestafffire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new AttackFireDamageFlat(), new CriticalDamageFlat(), new FirePeneFlat(),
		new LifeOnHitFlat());
    }

}
