package com.robertx22.generation;

import java.util.List;

import com.robertx22.database.lists.Rarities;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.uncommon.utilityclasses.IWeighted;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

public class RarityGen {

	public static ItemRarity Random(int MagicFind) {

		List<IWeighted> rarities = ListUtils.CollectionToList(Rarities.Items);

		ItemRarity rarity = (ItemRarity) RandomUtils.WeightedRandom(rarities);

		return rarity;

	}
}
