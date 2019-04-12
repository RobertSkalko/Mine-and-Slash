package com.robertx22.mmorpg.config;

import net.minecraftforge.common.config.Config;

public class DimensionConfigs {

    public DimensionConfigs() {

    }

    public static DimensionConfigs Overworld() {
	return new DimensionConfigs(0, 1, 100);
    }

    public static DimensionConfigs DefaultExtra() {
	return new DimensionConfigs(123456, 1, 100);
    }

    public static DimensionConfigs Nether() {
	return new DimensionConfigs(-1, 10, 100);
    }

    public static DimensionConfigs End() {
	return new DimensionConfigs(1, 25, 100);
    }

    public DimensionConfigs(int id, int min, int max) {
	this.DIMENSION_id = id;
	this.MINIMUM_MOB_LEVEL = min;
	this.MAXIMUM_MOB_LEVEL = max;
    }

    public DimensionConfigs(int id, int distance, int area, int min, int max) {
	this.DIMENSION_id = id;
	this.MOB_LEVEL_PER_DISTANCE = distance;
	this.MOB_LEVEL_ONE_AREA = area;
	this.MINIMUM_MOB_LEVEL = min;
	this.MAXIMUM_MOB_LEVEL = max;
    }

    @Config.Name("Dimension ID")
    @Config.LangKey("mmorpg.config.dimension_id")
    @Config.Comment("0 is overwold for example")
    public int DIMENSION_id = 0;

    @Config.Name("Mob Level Per Distance")
    @Config.LangKey("mmorpg.config.mob_lvl_per_distance")
    @Config.Comment("How fast you want mobs to level up based on distance. Higher value means slower leveling.")
    public int MOB_LEVEL_PER_DISTANCE = 250;

    @Config.Name("Mob Level One Area")
    @Config.LangKey("mmorpg.config.mob_lvl_one_area")
    @Config.Comment("How big you want level 1 mob area to be. Bigger value means bigger area")
    public int MOB_LEVEL_ONE_AREA = 50;

    @Config.Name("Maximum mob level")
    @Config.LangKey("mmorpg.config.maximum_mob_level")
    @Config.Comment("Select maximum level")
    public int MAXIMUM_MOB_LEVEL = 100;

    @Config.Name("Minimum mob level")
    @Config.LangKey("mmorpg.config.minimum_mob_level")
    @Config.Comment("Select maximum level for mobs in normal worlds like vanilla surface, nether, end. This doesn't affect max level for map worlds from my mod! Note: reasoning for low level cap here is to prevent BIS gear being farmable by mob spawners, which ruin the fun")
    public int MINIMUM_MOB_LEVEL = 1;

    @Config.Name("At What Level Mobs can be Legendary")
    @Config.LangKey("mmorpg.config.")
    @Config.Comment("")
    public int LEVEL_FOR_MOBS_TO_BE_LEGENDARY = 10;

    @Config.Name("At What Level Mobs can be Mythical")
    @Config.LangKey("mmorpg.config.")
    @Config.Comment("")
    public int LEVEL_FOR_MOBS_TO_BE_MYTHICAL = 20;

    @Config.Name("Singleplayer Mob Scaling")
    @Config.LangKey("mmorpg.config.singleplayer_mob_scaling")
    @Config.Comment("If you want mobs to always be at your level (closest nearby player). If you enable this it's recommended to enable requiring tokens for levelup as that gives you the option to stay at a low level to get geared.")
    public boolean SINGLEPLAYER_MOB_SCALING = false;

}
