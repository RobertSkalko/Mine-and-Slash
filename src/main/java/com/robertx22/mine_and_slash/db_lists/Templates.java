package com.robertx22.mine_and_slash.db_lists;

import com.robertx22.mine_and_slash.world_gen.types.FeatureType;

public class Templates {

    static final String treasure = "treasure/";
    static final String decoration = "decoration/";
    static final String onechunk = "one_chunk/";

    public static final FeatureType bigWoodPillar = new FeatureType(decoration + "big_wood_pillar0")
            .lowerBy(2);
    public static final FeatureType smallWoodPillar = new FeatureType(decoration + "small_wood_pillar0")
            .lowerBy(2);
    public static final FeatureType lampPillar = new FeatureType(decoration + "lamp").lowerBy(2);
    public static final FeatureType smallTreasure0 = new FeatureType(treasure + "small0").lowerBy(2);
    public static final FeatureType smallTreasure1 = new FeatureType(treasure + "small1");
    public static final FeatureType smallTreasure2 = new FeatureType(treasure + "trapped0")
            .lowerBy(2);

    public static final FeatureType dun0 = new FeatureType(onechunk + "dungeon0").lowerBy(9);
    public static final FeatureType dun1 = new FeatureType(onechunk + "dungeon1").lowerBy(9);

}
