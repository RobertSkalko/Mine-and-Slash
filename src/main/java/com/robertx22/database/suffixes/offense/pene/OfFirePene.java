package com.robertx22.database.suffixes.offense.pene;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.bonus.FireSpellToAttackFlat;
import com.robertx22.database.stat_mods.flat.elemental.pene.FirePeneFlat;
import com.robertx22.saveclasses.gearitem.gear_bases.Suffix;
import com.robertx22.stats.StatMod;

public class OfFirePene extends Suffix {

    @Override
    public String GUID() {
	return "Of Firestorms";
    }

    @Override
    public List<StatMod> StatMods() {

	return Arrays.asList(new FirePeneFlat(), new FireSpellToAttackFlat());

    }

    @Override
    public int Weight() {
	return this.LegendaryWeight;
    }

}
