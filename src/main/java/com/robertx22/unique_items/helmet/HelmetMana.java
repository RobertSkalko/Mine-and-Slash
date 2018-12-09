package com.robertx22.unique_items.helmet;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stat_mods.flat.MajorDodgeFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stat_mods.flat.resources.MajorManaRegenFlat;
import com.robertx22.database.stat_mods.flat.resources.ManaFlat;
import com.robertx22.database.stat_mods.flat.weapon_damages.StaffDamageFlat;
import com.robertx22.database.stat_mods.percent.ManaRegenPercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueHelmet;

public class HelmetMana extends BaseUniqueHelmet {

    public HelmetMana() {

    }

    @Override
    public int Tier() {
	return 17;
    }

    @Override
    public String GUID() {
	return "helmetmana0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new HealthFlat(), new ManaFlat(), new MajorManaRegenFlat(), new ManaRegenPercent(),
		new MajorDodgeFlat(), new ArmorFlat(), new StaffDamageFlat());
    }

}