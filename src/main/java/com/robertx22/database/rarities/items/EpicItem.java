package com.robertx22.database.rarities.items;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.database.rarities.base.BaseEpic;

public class EpicItem extends BaseEpic implements ItemRarity {
    public EpicItem() {
    }

    @Override
    public int AffixChance() {
	return 60;
    }

    @Override
    public MinMax SecondaryStatsAmount() {
	return new MinMax(2, 3);
    }

    @Override
    public MinMax StatPercents() {
	return new MinMax(20, 75);
    }

    @Override
    public int SetChance() {
	return 30;
    }

}
