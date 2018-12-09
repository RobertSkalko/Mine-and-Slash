package com.robertx22.unique_items.chest;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellFireDamageFlat;
import com.robertx22.database.stat_mods.flat.elemental.transfers.NatureToFireTransferFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.database.stat_mods.percent.much_less.CrippleLifestealPercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueChest;

public class ChestFire extends BaseUniqueChest {

    public ChestFire() {

    }

    @Override
    public int Tier() {
	return 6;

    }

    @Override
    public String GUID() {
	return "chestfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new HealthFlat(), new FireResistFlat(), new SpellFireDamageFlat(),
		new NatureToFireTransferFlat(), new CrippleLifeOnHitPercent(), new CrippleLifestealPercent());
    }

}