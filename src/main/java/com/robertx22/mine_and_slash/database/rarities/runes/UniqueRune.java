package com.robertx22.mine_and_slash.database.rarities.runes;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.rarities.RuneRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseUnique;

public class UniqueRune extends BaseUnique implements RuneRarity {

    private UniqueRune() {
    }

    public static UniqueRune getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public MinMax StatPercents() {
        return new MinMax(25, 100);
    }

    @Override
    public int Weight() {
        return 0;
    }

    @Override
    public float specialItemChance() {
        return 50;
    }

    private static class SingletonHolder {
        private static final UniqueRune INSTANCE = new UniqueRune();
    }
}

