package com.robertx22.database.suffixes.offense.pene;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.bonus.NatureSpellToAttackFlat;
import com.robertx22.database.stat_mods.flat.elemental.pene.NaturePeneFlat;
import com.robertx22.saveclasses.gearitem.gear_bases.Suffix;
import com.robertx22.stats.StatMod;

public class OfNaturePene extends Suffix {

    @Override
    public String GUID() {
	return "Of Earthquakes";
    }

    @Override
    public List<StatMod> StatMods() {

	return Arrays.asList(new NaturePeneFlat(), new NatureSpellToAttackFlat());

    }

    @Override
    public int Weight() {
	return this.LegendaryWeight;
    }

}
