package com.robertx22.mine_and_slash.database;

import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

public class MinMax {

    public int Min;
    public int Max;

    public MinMax(int min, int max) {
        this.Min = min;
        this.Max = max;
    }

    public boolean isInRange(int num) {

        if (num >= Min) {
            if (num <= Max) {
                return true;
            }
        }
        return false;

    }

    public int genPercent() {
        return RandomUtils.RandomRange(Min, Max);
    }
}
