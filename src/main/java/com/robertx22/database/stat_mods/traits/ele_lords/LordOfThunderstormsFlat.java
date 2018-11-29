package com.robertx22.database.stat_mods.traits.ele_lords;

import com.robertx22.database.stat_types.traits.ele_lords.LordOfThunderstormsTrait;
import com.robertx22.stats.Stat;

public class LordOfThunderstormsFlat extends BaseLordTrait {

    @Override
    public String GUID() {
	return "LordOfThunderstormsTrait";
    }

    @Override
    public Stat GetBaseStat() {
	return new LordOfThunderstormsTrait();
    }

}
