package com.robertx22.database.stat_types.traits.atronachs;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.multi.elemental.damage.SpellNatureDamageMulti;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.StatMod;
import com.robertx22.stats.Trait;

public class EarthAtronach extends Trait implements IAffectsOtherStats {

    public static String GUID = "Earth Atronach";

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public List<StatMod> getStats() {
	return Arrays.asList(new SpellNatureDamageMulti());

    }

    @Override
    public String Description() {
	return "";
    }

    @Override
    public String unlocString() {
	return "earth_atronach";
    }
}
