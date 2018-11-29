package com.robertx22.database.stat_mods.traits.ele_lords;

import com.robertx22.database.stat_mods.BaseTraitMod;
import com.robertx22.uncommon.utilityclasses.IWeighted;

public abstract class BaseLordTrait extends BaseTraitMod {

    @Override
    public int Weight() {
	return IWeighted.EpicWeight;
    }

}
