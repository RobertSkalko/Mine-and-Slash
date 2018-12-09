package com.robertx22.unique_items.axes;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.attack_dmg.AttackFireDamageFlat;
import com.robertx22.database.stat_mods.flat.elemental.attack_dmg.AttackWaterDamageFlat;
import com.robertx22.database.stat_mods.percent.CriticalHitPercent;
import com.robertx22.database.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.database.stat_mods.percent.much_less.CrippleManaOnHitPercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueAxe;

public class AxeWaterFire extends BaseUniqueAxe {
    public AxeWaterFire() {

    }

    @Override
    public int Tier() {
	return 16;
    }

    @Override
    public String GUID() {
	return "axewaterfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new AttackFireDamageFlat(), new AttackWaterDamageFlat(), new CriticalHitPercent(),
		new CrippleLifeOnHitPercent(), new CrippleManaOnHitPercent());
    }

}
