package com.robertx22.generation;

import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.generation.blueprints.RuneBlueprint;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.rune.RuneItemData;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.datasaving.Rune;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import net.minecraft.item.ItemStack;

public class RuneGen {

    public static ItemStack Create(RuneBlueprint blueprint) {

	int rarity = blueprint.GetRarity();

	BaseRuneItem item = blueprint.getRuneItem().byRarity(rarity);

	ItemStack stack = new ItemStack(item);

	RuneItemData data = new RuneItemData();

	data.rarity = item.rarity;
	data.name = item.name();
	data.level = blueprint.level;

	data.armor = StatModData.NewRandom(data.GetRarity(),
		(StatMod) RandomUtils.WeightedRandom(ListUtils.CollectionToList(item.armorStat())));

	data.weapon = StatModData.NewRandom(data.GetRarity(),
		(StatMod) RandomUtils.WeightedRandom(ListUtils.CollectionToList(item.weaponStat())));

	data.jewerly = StatModData.NewRandom(data.GetRarity(),
		(StatMod) RandomUtils.WeightedRandom(ListUtils.CollectionToList(item.jewerlyStat())));

	Rune.Save(stack, data);

	return stack;

    }

}
