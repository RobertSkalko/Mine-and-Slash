package com.robertx22.database.rarities.maps;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.MapRarity;
import com.robertx22.database.rarities.base.BaseUncommon;

public class UncommonMap extends BaseUncommon implements MapRarity {

    @Override
    public MinMax AffixAmount() {
	return new MinMax(2, 3);
    }

    @Override
    public MinMax StatPercents() {
	return new MinMax(15, 35);
    }

    @Override
    public int Weight() {
	return super.Weight() - 1500;
    }

    @Override
    public float specialItemChance() {
	return 3.5F;
    }

}
