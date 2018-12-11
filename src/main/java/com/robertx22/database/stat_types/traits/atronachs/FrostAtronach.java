package com.robertx22.database.stat_types.traits.atronachs;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.multi.elemental.damage.SpellWaterDamageMulti;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.StatMod;
import com.robertx22.stats.Trait;

public class FrostAtronach extends Trait implements IAffectsOtherStats {

    public static String GUID = "Frost Atronach";

    @Override
    public String unlocString() {
	return "frost_atronach";
    }

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public List<StatMod> getStats() {
	return Arrays.asList(new SpellWaterDamageMulti());

    }

    @Override
    public String Description() {
	return "";
    }
}
