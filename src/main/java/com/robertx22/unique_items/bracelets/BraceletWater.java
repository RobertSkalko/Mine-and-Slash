package com.robertx22.unique_items.bracelets;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellWaterDamageFlat;
import com.robertx22.database.stat_mods.flat.elemental.transfers.FireToWaterTransferFlat;
import com.robertx22.database.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueBracelet;

public class BraceletWater extends BaseUniqueBracelet {

    public BraceletWater() {

    }

    @Override
    public int Tier() {
	return 8;
    }

    @Override
    public String name() {
	return "Frostburn Bracers";
    }

    @Override
    public String GUID() {
	return "braceletwater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new SpellWaterDamageFlat(), new FireToWaterTransferFlat(), new EnergyRegenFlat(),
		new WaterResistFlat());
    }

    @Override
    public String description() {
	return "Burn them all! With Ice of course.";
    }

}
