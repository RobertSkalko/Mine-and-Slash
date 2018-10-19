package com.robertx22.generation;

import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.gearitem.GearItemSlot;
import com.robertx22.generation.blueprints.GearBlueprint;
import com.robertx22.saveclasses.gearitem.GearItemData;
import com.robertx22.saveclasses.gearitem.PrefixData;
import com.robertx22.saveclasses.gearitem.PrimaryStatsData;
import com.robertx22.saveclasses.gearitem.SecondaryStatsData;
import com.robertx22.saveclasses.gearitem.SuffixData;
import com.robertx22.uncommon.datasaving.GearSaving;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import net.minecraft.item.ItemStack;

public class GearGen {

	public static ItemStack Create(GearBlueprint schema) {

		GearItemSlot gearslot = schema.GetGearType();

		ItemRarity rarity = schema.GetRarity();

		ItemStack stack = gearslot.GetItemForRarity(rarity.Rank());
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

		GearSaving.Save(stack, data);

		stack.setStackDisplayName(data.GetDisplayName());

		return stack;

	}

}
