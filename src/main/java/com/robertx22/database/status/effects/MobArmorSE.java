package com.robertx22.database.status.effects;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.ArmorFlat;
import com.robertx22.database.status.effects.bases.BaseStatusEffect;
import com.robertx22.saveclasses.gearitem.StatModData;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class MobArmorSE extends BaseStatusEffect {

	@Override
	public Item ItemModel() {
		return Items.IRON_CHESTPLATE;
	}

	@Override
	public String GUID() {
		return "MobArmorSE";
	}

	@Override
	public List<StatModData> Stats() {
		return Arrays.asList(StatModData.NewStatusEffect(300, new ArmorFlat()));
	}

}
