package com.robertx22.unique_items.staffs;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.CriticalDamageFlat;
import com.robertx22.database.stat_mods.flat.DodgeFlat;
import com.robertx22.database.stat_mods.flat.elemental.attack_dmg.AttackThunderDamageFlat;
import com.robertx22.database.stat_mods.flat.resources.ManaOnHitFlat;
import com.robertx22.database.stat_mods.percent.pene.ThunderPenePercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueStaff;

public class StaffThunder extends BaseUniqueStaff {

    public StaffThunder() {

    }

    @Override
    public int Tier() {
	return 5;
    }

    @Override
    public String GUID() {
	return "uniquestaffthunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new AttackThunderDamageFlat(), new CriticalDamageFlat(), new ThunderPenePercent(),
		new DodgeFlat(), new ManaOnHitFlat());
    }

}
