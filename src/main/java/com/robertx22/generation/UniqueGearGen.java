package com.robertx22.generation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.database.rarities.items.Unique;
import com.robertx22.db_lists.GearTypes;
import com.robertx22.generation.blueprints.GearBlueprint;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.PrefixData;
import com.robertx22.saveclasses.gearitem.SuffixData;
import com.robertx22.saveclasses.gearitem.UniqueStatsData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.utilityclasses.IWeighted;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import com.robertx22.unique_items.IUnique;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class UniqueGearGen {

    public static GearItemData CreateData(GearBlueprint blueprint) {

	IUnique unique = randomUnique(blueprint);
	GearItemData data = new GearItemData();

	if (unique != null) {
	    ItemRarity rarity = new Unique();
	    GearItemSlot gearslot = GearTypes.All.get(unique.slot());

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

    public static IUnique randomUnique(GearBlueprint blueprint) {

	List<IWeighted> list = new ArrayList<IWeighted>();

	List<IUnique> possible = filterUniquesByType(blueprint.gearType,
		getAllPossibleUniqueDrops(blueprint.tier, IUnique.ITEMS.values()));

	IUnique unique = (IUnique) RandomUtils.WeightedRandom(ListUtils.CollectionToList(possible));

	return unique;

    }

    public static List<IUnique> getAllPossibleUniqueDrops(int tier, Collection<Item> coll) {
	List<IUnique> list = new ArrayList<IUnique>();

	for (Item item : coll) {
	    IUnique baseu = (IUnique) item;

	    if (tier >= baseu.Tier()) {
		list.add((IUnique) item);
	    }
	}
	return list;
    }

    public static List<IUnique> filterUniquesByType(String type, List<IUnique> coll) {

	List<IUnique> list = new ArrayList<IUnique>();

	for (IUnique item : coll) {
	    if (item.slot().equals(type) || type.equals("random") || type.equals("")) {
		list.add((IUnique) item);
	    }
	}

	return list;
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
