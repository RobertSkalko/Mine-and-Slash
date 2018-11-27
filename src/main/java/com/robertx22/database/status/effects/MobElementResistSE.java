package com.robertx22.database.status.effects;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.database.status.effects.bases.BaseStatusEffect;
import com.robertx22.saveclasses.gearitem.StatModData;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class MobElementResistSE extends BaseStatusEffect {

	@Override
	public Item ItemModel() {
		return Items.GOLDEN_CHESTPLATE;
	}

	@Override
	public String GUID() {
		return "MobElementResistSE";
	}

	int percent = 300;

	@Override
	public List<StatModData> Stats() {
		return Arrays.asList(StatModData.NewStatusEffect(percent, new FireResistFlat()),
				StatModData.NewStatusEffect(percent, new WaterResistFlat()),
				StatModData.NewStatusEffect(percent, new ThunderResistFlat()),
				StatModData.NewStatusEffect(percent, new NatureResistFlat()));
	}

}
