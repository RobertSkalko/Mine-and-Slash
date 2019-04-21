package com.robertx22.database.gearitemslots.bases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stat_mods.flat.BlockStrengthFlat;
import com.robertx22.db_lists.Prefixes;
import com.robertx22.db_lists.Suffixes;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.saveclasses.gearitem.gear_bases.Suffix;
import com.robertx22.stats.StatMod;

public abstract class BaseOffHand extends GearItemSlot {

    @Override
    public List<Suffix> PossibleSuffixes() {
	return new ArrayList<Suffix>(Suffixes.Armor);
    }

    @Override
    public List<Prefix> PossiblePrefixes() {
	return new ArrayList<Prefix>(Prefixes.Armor);
    }

    @Override
    public List<StatMod> PrimaryStats() {
	return Arrays.asList(new BlockStrengthFlat());
    }

    @Override
    public List<StatMod> PossibleSecondaryStats() {
	return Arrays.asList(new ArmorFlat());
    }

    @Override
    public GearSlotType slotType() {
	return GearSlotType.OffHand;
    }
}
