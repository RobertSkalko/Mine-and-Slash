package com.robertx22.unique_items.swords;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.attack_dmg.AttackWaterDamageFlat;
import com.robertx22.database.stat_mods.flat.elemental.transfers.ThunderToWaterTransferFlat;
import com.robertx22.database.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stat_mods.flat.resources.ManaOnHitFlat;
import com.robertx22.database.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.database.stat_mods.percent.much_less.CrippleLifestealPercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueSword;

public class SwordWater extends BaseUniqueSword {
    public SwordWater() {

    }

    @Override
    public int Tier() {
	return 6;
    }

    @Override
    public String GUID() {
	return "swordwater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new AttackWaterDamageFlat(), new ThunderToWaterTransferFlat(), new ManaRegenFlat(),
		new ManaOnHitFlat(), new EnergyRegenFlat(), new CrippleLifestealPercent());
    }

}
