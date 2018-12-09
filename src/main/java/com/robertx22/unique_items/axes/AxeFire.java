package com.robertx22.unique_items.axes;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.attack_dmg.AttackFireDamageFlat;
import com.robertx22.database.stat_mods.flat.elemental.pene.FirePeneFlat;
import com.robertx22.database.stat_mods.percent.CriticalDamagePercent;
import com.robertx22.database.stat_mods.percent.CriticalHitPercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueAxe;

public class AxeFire extends BaseUniqueAxe {
    public AxeFire() {

    }

    @Override
    public int Tier() {
	return 3;
    }

    @Override
    public String GUID() {
	return "axefire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new AttackFireDamageFlat(), new CriticalHitPercent(), new CriticalDamagePercent(),
		new FirePeneFlat());
    }

}
