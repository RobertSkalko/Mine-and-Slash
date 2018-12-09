package com.robertx22.unique_items.staffs;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.attack_dmg.AttackNatureDamageFlat;
import com.robertx22.database.stat_mods.flat.elemental.pene.NaturePeneFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stat_mods.percent.pene.NaturePenePercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueStaff;

public class StaffNature extends BaseUniqueStaff {

    public StaffNature() {

    }

    @Override
    public int Tier() {
	return 5;
    }

    @Override
    public String GUID() {
	return "uniquestaffnature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new AttackNatureDamageFlat(), new NaturePeneFlat(), new NaturePenePercent(),
		new HealthRegenFlat());
    }

}
