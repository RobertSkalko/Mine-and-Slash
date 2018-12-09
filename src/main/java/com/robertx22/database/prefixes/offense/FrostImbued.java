package com.robertx22.database.prefixes.offense;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.bonus.WaterSpellToAttackFlat;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.stats.StatMod;

public class FrostImbued extends Prefix {

    public FrostImbued() {
    }

    @Override
    public String GUID() {
	return "Frost Imbued";
    }

    @Override
    public List<StatMod> StatMods() {

	return Arrays.asList(new WaterSpellToAttackFlat());
    }

}
