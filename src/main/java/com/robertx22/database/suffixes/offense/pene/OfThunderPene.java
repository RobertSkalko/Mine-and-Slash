package com.robertx22.database.suffixes.offense.pene;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.bonus.ThunderSpellToAttackFlat;
import com.robertx22.database.stat_mods.flat.elemental.pene.ThunderPeneFlat;
import com.robertx22.saveclasses.gearitem.gear_bases.Suffix;
import com.robertx22.stats.StatMod;

public class OfThunderPene extends Suffix {

    @Override
    public String GUID() {
	return "Of Thunderstorms";
    }

    @Override
    public List<StatMod> StatMods() {

	return Arrays.asList(new ThunderPeneFlat(), new ThunderSpellToAttackFlat());

    }

    @Override
    public int Weight() {
	return this.LegendaryWeight;
    }

}
