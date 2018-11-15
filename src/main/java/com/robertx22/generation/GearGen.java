package com.robertx22.generation;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.lists.GearTypes;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.generation.blueprints.GearBlueprint;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.PrefixData;
import com.robertx22.saveclasses.gearitem.PrimaryStatsData;
import com.robertx22.saveclasses.gearitem.SecondaryStatsData;
import com.robertx22.saveclasses.gearitem.SuffixData;
import com.robertx22.uncommon.datasaving.GearSaving;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import net.minecraft.item.ItemStack;

public class GearGen {

	public static GearItemData CreateData(GearBlueprint schema) {
		GearItemSlot gearslot = schema.GetGearType();

		ItemRarity rarity = schema.GetRarity();

		GearItemData data = new GearItemData();

		data.level = schema.GetLevel();
		data.gearTypeName = gearslot.Name();
		data.Rarity = rarity.Rank();
		data.name = gearslot.Name();

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

		data.set = schema.GenerateSet();

		return data;
	}

	public static ItemStack CreateStack(GearBlueprint schema) {

		GearItemData data = CreateData(schema);

		ItemStack stack = GearTypes.All.get(data.gearTypeName).GetItemForRarity(data.GetRarity().Rank());

		GearSaving.Save(stack, data);

		stack.setStackDisplayName(data.GetDisplayName());

		return stack;

	}

	public static ItemStack CreateStack(GearItemData data) {

		ItemStack stack = GearTypes.All.get(data.gearTypeName).GetItemForRarity(data.GetRarity().Rank());

		GearSaving.Save(stack, data);

		stack.setStackDisplayName(data.GetDisplayName());

		return stack;

	}

}
