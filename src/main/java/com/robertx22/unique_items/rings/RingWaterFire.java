package com.robertx22.unique_items.rings;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.conversions.FireToWaterConvFlat;
import com.robertx22.database.stat_mods.flat.elemental.conversions.WaterToFireConvFlat;
import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellFireDamageFlat;
import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellWaterDamageFlat;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueRing;

public class RingWaterFire extends BaseUniqueRing {

    public RingWaterFire() {

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
