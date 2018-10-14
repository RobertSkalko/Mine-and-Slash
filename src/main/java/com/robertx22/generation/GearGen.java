package com.robertx22.generation;

import java.util.List;

import com.robertx22.database.lists.GearTypes;
import com.robertx22.gearitem.GearItemSlot;
import com.robertx22.gearitem.ItemRarity;
import com.robertx22.interfaces.IWeighted;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.PrefixData;
import com.robertx22.saveclasses.PrimaryStatsData;
import com.robertx22.saveclasses.SecondaryStatsData;
import com.robertx22.saveclasses.SuffixData;
import com.robertx22.saving.Saving;
import com.robertx22.utilityclasses.ListUtils;
import com.robertx22.utilityclasses.RandomUtils;
import com.robertx22.utilityclasses.WeightedUtils;

import net.minecraft.item.ItemStack;

public class GearGen {

	public static ItemStack Random() {

		List<IWeighted> slots = ListUtils.CollectionToList(GearTypes.All.values());

		GearItemSlot gearslot = (GearItemSlot) WeightedUtils.WeightedRandom(slots);

		ItemRarity rarity = RarityGen.Random(0);

		System.out.println("rarity:" + rarity.Rank());

		ItemStack stack = gearslot.GetItemForRarity(rarity.Rank());
		GearItemData data = new GearItemData();

		data.level = 1; // TODO
		data.gearTypeName = gearslot.Name();
		data.Rarity = rarity.Rank();

		data.primaryStats = new PrimaryStatsData();
		data.primaryStats.RerollFully(data);

		data.secondaryStats = new SecondaryStatsData();
		data.secondaryStats.RerollFully(data);

		if (RandomUtils.roll(rarity.AffixChance())) {

			data.suffix = new SuffixData();
			data.suffix.RerollFully(data);

		}
		if (RandomUtils.roll(rarity.AffixChance())) {

			data.prefix = new PrefixData();
			data.prefix.RerollFully(data);

		}

		Saving.SaveToItem(stack, data);

		stack.setStackDisplayName(data.GetDisplayName());

		return stack;

	}

}
