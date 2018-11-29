package com.robertx22.database.rarities.items;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.database.rarities.base.BaseCommon;

public class CommonItem extends BaseCommon implements ItemRarity {

    @Override
    public int AffixChance() {
	return 10;
    }

    @Override
    public MinMax SecondaryStatsAmount() {
	return new MinMax(0, 1);
    }

    @Override
    public MinMax StatPercents() {
	return new MinMax(3, 60);
    }

    @Override
    public int SetChance() {
	return 5;
    }

}
