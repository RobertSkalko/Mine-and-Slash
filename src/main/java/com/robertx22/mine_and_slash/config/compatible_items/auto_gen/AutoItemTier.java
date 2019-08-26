package com.robertx22.mine_and_slash.config.compatible_items.auto_gen;

public class AutoItemTier {

    public AutoItemTier(int tier, float breakpoint) {
        this.tier = tier;
        this.percentBreakpointToTier = breakpoint;
    }

    public float percentBreakpointToTier;
    public int tier = 0;
    public int minRar = 0;
    public int maxRar = 5;
    public int maxLvl = 100;
    public int minLvl = 0;

    public AutoItemTier lvl(int min, int max) {
        this.minLvl = min;
        this.maxLvl = max;
        return this;
    }

    public AutoItemTier rar(int min, int max) {
        this.minRar = min;
        this.maxRar = max;
        return this;
    }

}
