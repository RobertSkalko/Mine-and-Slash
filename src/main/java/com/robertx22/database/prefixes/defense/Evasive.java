package com.robertx22.database.prefixes.defense;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.DodgeFlat;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.stats.StatMod;

public class Evasive extends Prefix {

    public Evasive() {
    }

    @Override
    public String GUID() {
	return "Evasive";
    }

    @Override
    public List<StatMod> StatMods() {
	return Arrays.asList(new DodgeFlat());
    }

}
