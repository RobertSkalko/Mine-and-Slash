package com.robertx22.customitems.infusions;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stat_mods.flat.resources.LifeOnHitFlat;
import com.robertx22.database.stat_mods.flat.resources.LifestealFlat;
import com.robertx22.database.stat_mods.flat.resources.ManaFlat;
import com.robertx22.database.stat_mods.flat.resources.ManaOnHitFlat;
import com.robertx22.database.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.stats.StatMod;

public class ResourceInfusionItem extends BaseInfusionItem {

    public ResourceInfusionItem() {
	super("resource_infusion");

    }

    @Override
    public List<StatMod> weaponInfusions() {
	return Arrays.asList(new ManaOnHitFlat(), new LifeOnHitFlat(), new LifestealFlat());
    }

    @Override
    public List<StatMod> armorInfusions() {
	return Arrays.asList(new ManaRegenFlat(), new EnergyRegenFlat(), new HealthRegenFlat());
    }

    @Override
    public List<StatMod> jewerlyInfusions() {
	return Arrays.asList(new ManaFlat(), new HealthRegenFlat());
    }

}
