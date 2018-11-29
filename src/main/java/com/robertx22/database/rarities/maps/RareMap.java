package com.robertx22.database.rarities.maps;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.MapRarity;
import com.robertx22.database.rarities.base.BaseRare;

public class RareMap extends BaseRare implements MapRarity {

    @Override
    public MinMax AffixAmount() {
	return new MinMax(3, 4);
    }

    @Override
    public MinMax StatPercents() {
	return new MinMax(20, 45);
    }

}
