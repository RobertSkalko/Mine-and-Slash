package com.robertx22.generation;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.database.rarities.items.Unique;
import com.robertx22.generation.blueprints.GearBlueprint;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.PrefixData;
import com.robertx22.saveclasses.gearitem.SuffixData;
import com.robertx22.saveclasses.gearitem.UniqueStatsData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.utilityclasses.IWeighted;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import com.robertx22.unique_items.bases.BaseUniqueItem;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class UniqueGearGen {

	public static GearItemData CreateData(GearBlueprint blueprint) {

		BaseUniqueItem unique = randomUnique(blueprint);
		GearItemData data = new GearItemData();

		if (unique != null) {
			ItemRarity rarity = new Unique();
			GearItemSlot gearslot = blueprint.GetGearType();

			data.isUnique = true;

			data.uniqueGUID = unique.GUID();
			data.uniqueStats = new UniqueStatsData(unique.GUID());
			data.uniqueStats.RerollFully(data);

			data.level = blueprint.GetLevel();
			data.gearTypeName = gearslot.Name();
			data.Rarity = rarity.Rank();
			data.name = gearslot.Name();

			data.gearTypeStats = blueprint.genGearTypeStats(data);

			if (RandomUtils.roll(rarity.AffixChance())) {

				data.suffix = new SuffixData();
				data.suffix.RerollFully(data);

			}
			if (RandomUtils.roll(rarity.AffixChance())) {

				data.prefix = new PrefixData();
				data.prefix.RerollFully(data);

			}
		}

		return data;
	}

	public static BaseUniqueItem randomUnique(GearBlueprint blueprint) {

		List<IWeighted> list = new ArrayList<IWeighted>();

		for (Item item : BaseUniqueItem.ITEMS.values()) {
			BaseUniqueItem baseu = (BaseUniqueItem) item;

			if (baseu.Tier() <= blueprint.tier) {
				if (baseu.slot().equals(blueprint.gearType) || blueprint.gearType.equals("random")) {
					list.add((IWeighted) item);
				}
			}
		}

		BaseUniqueItem unique = (BaseUniqueItem) RandomUtils.WeightedRandom(list);

		blueprint.gearType = unique.slot();

		return unique;

	}

	public static ItemStack CreateStack(GearBlueprint schema) {

		GearItemData data = CreateData(schema);

		ItemStack stack = new ItemStack(data.getItem());

		Gear.Save(stack, data);

		stack.setStackDisplayName(data.GetDisplayName());

		return stack;

	}

	public static ItemStack CreateStack(GearItemData data) {

		ItemStack stack = new ItemStack(data.getItem());

		Gear.Save(stack, data);

		stack.setStackDisplayName(data.GetDisplayName());

		return stack;

	}

}
