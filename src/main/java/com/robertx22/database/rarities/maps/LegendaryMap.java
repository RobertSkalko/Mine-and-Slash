package com.robertx22.database.rarities.maps;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.MapRarity;
import com.robertx22.database.rarities.base.BaseLegendary;

public class LegendaryMap extends BaseLegendary implements MapRarity {

    @Override
    public MinMax AffixAmount() {
	return new MinMax(5, 6);
    }

    @Override
    public MinMax StatPercents() {
	return new MinMax(40, 70);
    }

}