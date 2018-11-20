package com.robertx22.generation;

import com.robertx22.customitems.misc.ItemMap;
import com.robertx22.database.lists.Rarities;
import com.robertx22.database.rarities.MapRarity;
import com.robertx22.generation.blueprints.MapBlueprint;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.uncommon.datasaving.Map;

import net.minecraft.item.ItemStack;

public class MapGen {
	public static ItemStack Create(MapBlueprint blueprint) {

		MapItemData data = new MapItemData();
		MapRarity rarity = Rarities.Maps.get(blueprint.GetRarity());
		ItemStack stack = new ItemStack(ItemMap.Items.get(rarity.Rank()));

		data.rarity = rarity.Rank();

		data.worldGeneratorName = "";

		data.level = blueprint.GetLevel();

		stack.setStackDisplayName(rarity.Color() + rarity.Name() + " " + data.name);

		Map.Save(stack, data);

		return stack;

	}

}
