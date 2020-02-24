package com.robertx22.database.stat_types.traits.atronachs;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.multi.elemental.damage.SpellThunderDamageMulti;
import com.robertx22.database.stats.IAffectsOtherStats;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.Trait;

public class ThunderAtronach extends Trait implements IAffectsOtherStats {

    public static String GUID = "Thunder Atronach";

    @Override
    public String unlocString() {
	return "thunder_atronach";
    }

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public List<StatMod> getStats() {
	return Arrays.asList(new SpellThunderDamageMulti());

    }

    @Override
    public String Description() {
	return "";
    }
}
