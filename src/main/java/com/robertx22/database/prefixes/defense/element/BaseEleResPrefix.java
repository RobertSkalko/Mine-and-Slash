package com.robertx22.database.prefixes.defense.element;

import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;

public abstract class BaseEleResPrefix extends Prefix {

    @Override
    public int Weight() {
	return this.EpicWeight;
    }

}
