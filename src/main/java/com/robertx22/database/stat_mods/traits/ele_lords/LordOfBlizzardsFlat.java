package com.robertx22.database.stat_mods.traits.ele_lords;

import com.robertx22.database.stat_types.traits.ele_lords.LordOfBlizzardsTrait;
import com.robertx22.stats.Stat;

public class LordOfBlizzardsFlat extends BaseLordTrait {

    @Override
    public String GUID() {
	return "LordOfBlizzardsFlat";
    }

    @Override
    public Stat GetBaseStat() {
	return new LordOfBlizzardsTrait();
    }

}