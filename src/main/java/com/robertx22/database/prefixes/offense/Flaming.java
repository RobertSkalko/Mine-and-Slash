package com.robertx22.database.prefixes.offense;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.percent.spell_ele_dmg.SpellFireDamagePercent;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.stats.StatMod;

public class Flaming extends Prefix {

    public Flaming() {
    }

    @Override
    public String GUID() {
	return "Flaming";
    }

    @Override
    public List<StatMod> StatMods() {

	return Arrays.asList(new SpellFireDamagePercent());
    }

}
