package com.robertx22.database.rarities.items;

import com.robertx22.database.MinMax;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.database.rarities.base.BaseMythical;

public class MythicalItem extends BaseMythical implements ItemRarity {

    @Override
    public int AffixChance() {
	return 100;
    }

    @Override
    public MinMax SecondaryStatsAmount() {
	return new MinMax(3, 5);
    }

    @Override
    public MinMax StatPercents() {
	return new MinMax(35, 100);
    }

    @Override
    public int SetChance() {
	return 75;
    }

}
