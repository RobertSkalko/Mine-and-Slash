package com.robertx22.database.stat_mods.traits.ele_lords;

import com.robertx22.database.stat_types.traits.ele_lords.LordOfVolcanoesTrait;
import com.robertx22.stats.Stat;

public class LordOfVolcanoesFlat extends BaseLordTrait {

    @Override
    public String GUID() {
	return "LordOfVolcanoesFlat";
    }

    @Override
    public Stat GetBaseStat() {
	return new LordOfVolcanoesTrait();
    }

}
