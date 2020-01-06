package com.robertx22.items.unique_items.rings;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.conversions.FireToWaterConvFlat;
import com.robertx22.database.stat_mods.flat.elemental.conversions.WaterToFireConvFlat;
import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellFireDamageFlat;
import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellWaterDamageFlat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.items.unique_items.bases.BaseUniqueRing;

import baubles.api.BaubleType;

public class RingWaterFire extends BaseUniqueRing {

    public RingWaterFire(BaubleType type) {
		super(type);
    }

    @Override
    public int Tier() {
	return 15;
    }

    @Override
    public String GUID() {
	return "ringwaterfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new SpellFireDamageFlat(), new SpellWaterDamageFlat(), new FireToWaterConvFlat(),
		new WaterToFireConvFlat());
    }

}
