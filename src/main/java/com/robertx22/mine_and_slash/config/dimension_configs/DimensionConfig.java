package com.robertx22.mine_and_slash.config.dimension_configs;

public class DimensionConfig {

    public DimensionConfig() {

    }

    public static DimensionConfig Overworld() {
        return new DimensionConfig(1, 100);
    }

    public static DimensionConfig Nether() {
        return new DimensionConfig(75, 100, 10, 100);
    }

    public static DimensionConfig End() {
        return new DimensionConfig(25, 100);
    }

    public static DimensionConfig DefaultExtra() {
        return new DimensionConfig(1, 100);
    }

    public DimensionConfig(int min, int max) {
        this.MINIMUM_MOB_LEVEL = min;
        this.MAXIMUM_MOB_LEVEL = max;
    }

    public DimensionConfig(int distance, int area, int min, int max) {
        this.MOB_LEVEL_PER_DISTANCE = distance;
        this.MOB_LEVEL_ONE_AREA = area;
        this.MINIMUM_MOB_LEVEL = min;
        this.MAXIMUM_MOB_LEVEL = max;

    }

    public int MOB_LEVEL_PER_DISTANCE = 125;

    public int MOB_LEVEL_ONE_AREA = 50;

    public int MAXIMUM_MOB_LEVEL = 100;

    public int MINIMUM_MOB_LEVEL = 1;

    public int LEVEL_FOR_MOBS_TO_BE_LEGENDARY = 10;

    public int LEVEL_FOR_MOBS_TO_BE_MYTHICAL = 20;

    public boolean SINGLEPLAYER_MOB_SCALING = false;

    public boolean DROPS_UNIQUE_ITEMS = false;

    public int MAP_TIER = 0;

    public float DROP_MULTIPLIER = 1F;

    public float MOB_STRENGTH_MULTIPLIER = 1F;

}
