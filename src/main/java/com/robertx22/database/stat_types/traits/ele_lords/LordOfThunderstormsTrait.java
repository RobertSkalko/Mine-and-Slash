package com.robertx22.database.stat_types.traits.ele_lords;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.multi.elemental.all_damage.AllThunderDamageMulti;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.StatMod;
import com.robertx22.stats.Trait;

public class LordOfThunderstormsTrait extends Trait implements IAffectsOtherStats {

    @Override
    public List<StatMod> getStats() {

	return Arrays.asList(new AllThunderDamageMulti());

    }

    @Override
    public String Description() {
	return "";
    }

    @Override
    public String Guid() {
	return "Lord Of Thunderstorms";
    }

    @Override
    public String unlocString() {
	return "Lord_of_Thunderstorms";
    }

}
