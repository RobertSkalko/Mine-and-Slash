package com.robertx22.database.status.effects;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.percent.HealthPercent;
import com.robertx22.database.status.effects.bases.BaseStatusEffect;
import com.robertx22.saveclasses.gearitem.StatModData;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class MobHealthSE extends BaseStatusEffect {

	@Override
	public Item ItemModel() {
		return Items.APPLE;
	}

	@Override
	public String GUID() {
		return "HealthSE";
	}

	@Override
	public List<StatModData> Stats() {
		return Arrays.asList(StatModData.NewStatusEffect(500, new HealthPercent()));
	}

}
