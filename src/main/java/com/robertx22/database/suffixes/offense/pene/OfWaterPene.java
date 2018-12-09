package com.robertx22.database.suffixes.offense.pene;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.bonus.WaterSpellToAttackFlat;
import com.robertx22.database.stat_mods.flat.elemental.pene.WaterPeneFlat;
import com.robertx22.saveclasses.gearitem.gear_bases.Suffix;
import com.robertx22.stats.StatMod;

public class OfWaterPene extends Suffix {

    @Override
    public String GUID() {
	return "Of Icestorms";
    }

    @Override
    public List<StatMod> StatMods() {

	return Arrays.asList(new WaterPeneFlat(), new WaterSpellToAttackFlat());

    }

    @Override
    public int Weight() {
	return this.LegendaryWeight;
    }

}
