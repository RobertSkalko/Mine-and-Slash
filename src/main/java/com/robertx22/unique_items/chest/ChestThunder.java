package com.robertx22.unique_items.chest;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.MajorArmorFlat;
import com.robertx22.database.stat_mods.flat.elemental.bonus.ThunderSpellToAttackFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stat_mods.percent.much_less.CrippleLifestealPercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueChest;

public class ChestThunder extends BaseUniqueChest {

    public ChestThunder() {

    }

    @Override
    public int Tier() {
	return 6;
    }

    @Override
    public String name() {
	return "Armor of the Thunderstorm";
    }

    @Override
    public String GUID() {
	return "chestthunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new HealthFlat(), new ThunderResistFlat(), new MajorArmorFlat(),
		new ThunderSpellToAttackFlat(), new CrippleLifestealPercent());
    }

    @Override
    public String description() {
	return "Those who dared to follow had long since died.";
    }

}