package com.robertx22.database.gearitemslots;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.gearitems.baubles.ItemCharm;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stat_mods.flat.ArmorPeneFlat;
import com.robertx22.database.stat_mods.flat.elemental.pene.FirePeneFlat;
import com.robertx22.database.stat_mods.flat.elemental.pene.NaturePeneFlat;
import com.robertx22.database.stat_mods.flat.elemental.pene.ThunderPeneFlat;
import com.robertx22.database.stat_mods.flat.elemental.pene.WaterPeneFlat;
import com.robertx22.db_lists.Prefixes;
import com.robertx22.db_lists.Suffixes;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.saveclasses.gearitem.gear_bases.Suffix;
import com.robertx22.stats.StatMod;

import net.minecraft.item.Item;

public class Charm extends GearItemSlot {

    @Override
    public String GUID() {
	return "Charm";
    }

    @Override
    public List<Suffix> PossibleSuffixes() {
	return new ArrayList<Suffix>(Suffixes.Weapon);
    }

    @Override
    public List<Prefix> PossiblePrefixes() {
	return new ArrayList<Prefix>(Prefixes.Weapon);
    }

    @Override
    public List<StatMod> PrimaryStats() {
	return Arrays.asList(new FirePeneFlat(), new WaterPeneFlat(), new NaturePeneFlat(), new ThunderPeneFlat(),
		new ArmorPeneFlat());
    }

    @Override
    public List<StatMod> PossibleSecondaryStats() {
	return Arrays.asList(new ArmorFlat());
    }

    @Override
    public Item DefaultItem() {
	return ItemCharm.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
	return ItemCharm.Items;
    }

    @Override
    public int Weight() {
	return 1000;
    }

    @Override
    public GearSlotType slotType() {
	return GearSlotType.Jewerly;
    }
}
