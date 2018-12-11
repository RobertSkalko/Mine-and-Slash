package com.robertx22.database.stat_types.traits;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.multi.elemental.damage.SpellFireDamageMulti;
import com.robertx22.database.stat_mods.multi.elemental.damage.SpellNatureDamageMulti;
import com.robertx22.database.stat_mods.multi.elemental.damage.SpellThunderDamageMulti;
import com.robertx22.database.stat_mods.multi.elemental.damage.SpellWaterDamageMulti;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.StatMod;
import com.robertx22.stats.Trait;

public class Elemental extends Trait implements IAffectsOtherStats {

    public static String GUID = "Elemental";

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public int percent() {
	return 33;
    }

    @Override
    public List<StatMod> getStats() {
	return Arrays.asList(new SpellWaterDamageMulti(), new SpellNatureDamageMulti(), new SpellThunderDamageMulti(),
		new SpellFireDamageMulti());

    }

    @Override
    public String Description() {
	return "";
    }

    @Override
    public String unlocString() {
	return "Elemental";
    }

}
