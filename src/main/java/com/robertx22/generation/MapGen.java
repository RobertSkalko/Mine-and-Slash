package com.robertx22.generation;

import com.robertx22.customitems.misc.ItemMap;
import com.robertx22.database.map_affixes.BaseMapAffix;
import com.robertx22.database.rarities.MapRarity;
import com.robertx22.db_lists.MapAffixes;
import com.robertx22.db_lists.Rarities;
import com.robertx22.db_lists.WorldProviders;
import com.robertx22.dimensions.IWP;
import com.robertx22.generation.blueprints.MapBlueprint;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import com.robertx22.uncommon.datasaving.Map;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import net.minecraft.item.ItemStack;

public class MapGen {
	public static ItemStack Create(MapBlueprint blueprint) {

		MapItemData data = new MapItemData();
		MapRarity rarity = Rarities.Maps.get(blueprint.GetRarity());
		ItemStack stack = new ItemStack(ItemMap.Items.get(rarity.Rank()));

		data.rarity = rarity.Rank();

		IWP iwp = ((IWP) RandomUtils.WeightedRandom(ListUtils.CollectionToList(WorldProviders.All.values())));

		data.worldGeneratorName = iwp.GUID();

		data.level = blueprint.GetLevel();

		data = genAffixes(data, rarity);

		stack.setStackDisplayName(rarity.Color() + rarity.Name() + " " + data.name);

		Map.Save(stack, data);

		return stack;

	}

	private static MapItemData genAffixes(MapItemData map, MapRarity rarity) {

		int amount = RandomUtils.RandomRange(rarity.AffixAmount().Min, rarity.AffixAmount().Max);

		for (int i = 0; i < amount; i++) {

			BaseMapAffix affix = (BaseMapAffix) RandomUtils
					.WeightedRandom(ListUtils.CollectionToList(MapAffixes.All.values()));

			int percent = RandomUtils.RandomRange(rarity.StatPercents().Min, rarity.StatPercents().Max);

			map.affixes.add(new MapAffixData(affix, percent));

		}

		return map;
	}

}
