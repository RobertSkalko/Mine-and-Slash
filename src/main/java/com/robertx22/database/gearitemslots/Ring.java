package com.robertx22.database.gearitemslots;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.gearitems.baubles.ItemRing;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellFireDamageFlat;
import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellNatureDamageFlat;
import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellThunderDamageFlat;
import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellWaterDamageFlat;
import com.robertx22.database.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stat_mods.flat.resources.ManaFlat;
import com.robertx22.database.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.db_lists.Prefixes;
import com.robertx22.db_lists.Suffixes;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.saveclasses.gearitem.gear_bases.Suffix;
import com.robertx22.stats.StatMod;

import net.minecraft.item.Item;

public class Ring extends GearItemSlot {

    @Override
    public String GUID() {
	return "Ring";
    }

    @Override
    public List<Suffix> PossibleSuffixes() {
	return new ArrayList<Suffix>(Suffixes.Jewerly);
    }

    @Override
    public List<Prefix> PossiblePrefixes() {
	return new ArrayList<Prefix>(Prefixes.Jewerly);
    }

    @Override
    public List<StatMod> PrimaryStats() {
	return Arrays.asList(new SpellFireDamageFlat(), new SpellThunderDamageFlat(), new SpellWaterDamageFlat(),
		new SpellNatureDamageFlat());
    }

    @Override
    public List<StatMod> PossibleSecondaryStats() {
	return Arrays.asList(new EnergyRegenFlat(), new ManaRegenFlat(), new ManaFlat());
    }

    @Override
    public Item DefaultItem() {
	return ItemRing.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
	return ItemRing.Items;
    }

    @Override
    public int Weight() {
	return 1500;
    }

    @Override
    public GearSlotType slotType() {
	return GearSlotType.Jewerly;
    }
}
