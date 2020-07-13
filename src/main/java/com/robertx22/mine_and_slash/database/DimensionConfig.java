package com.robertx22.mine_and_slash.database;

import com.google.gson.JsonObject;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializable;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializedRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;

public class DimensionConfig implements ISerializedRegistryEntry<DimensionConfig>, ISerializable<DimensionConfig> {

    public DimensionConfig() {

    }

    public static DimensionConfig EMPTY = new DimensionConfig();

    public DimensionConfig(String dimension_id) {
        this.dimension_id = dimension_id;
    }

    public static DimensionConfig Overworld() {
        return new DimensionConfig("minecraft:overworld");
    }

    public static DimensionConfig Nether() {
        return new DimensionConfig("minecraft:the_nether").setMobTier(2);
    }

    public static DimensionConfig End() {
        return new DimensionConfig("minecraft:the_end").setMobTier(2);
    }

    public static DimensionConfig DefaultExtra() {
        DimensionConfig config = new DimensionConfig();
        return config;
    }

    public DimensionConfig setUniqueMulti(float m) {
        this.unique_gear_drop_multi = m;
        return this;
    }

    public DimensionConfig setMobTier(int t) {
        this.mob_tier = t;
        return this;
    }

    public String dimension_id = "";

    public boolean drops_unique_gear = true;

    public int mob_tier = 0;

    public float all_drop_multi = 1F;

    public float unique_gear_drop_multi = 1F;

    public float mob_strength_multi = 1F;

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.DIMENSION_CONFIGS;
    }

    @Override
    public String GUID() {
        return dimension_id;
    }

    @Override
    public int Weight() {
        return 1;
    }

    @Override
    public int getRarityRank() {
        return 0;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Gears.get(0);
    }

    @Override
    public int getTier() {
        return this.mob_tier;
    }

    public boolean isMapWorld() {
        return mob_tier > 0;
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = new JsonObject();

        json.addProperty("dimension_id", dimension_id);
        json.addProperty("drops_unique_gear", drops_unique_gear);
        json.addProperty("mob_tier", mob_tier);
        json.addProperty("all_drop_multi", all_drop_multi);
        json.addProperty("unique_gear_drop_multi", unique_gear_drop_multi);
        json.addProperty("mob_strength_multi", mob_strength_multi);

        return json;
    }

    @Override
    public DimensionConfig fromJson(JsonObject json) {

        try {

            DimensionConfig config = new DimensionConfig();

            config.dimension_id = json.get("dimension_id")
                .getAsString();
            config.drops_unique_gear = json.get("drops_unique_gear")
                .getAsBoolean();
            config.mob_tier = json.get("mob_tier")
                .getAsInt();
            config.all_drop_multi = json.get("all_drop_multi")
                .getAsFloat();
            config.unique_gear_drop_multi = json.get("unique_gear_drop_multi")
                .getAsFloat();
            config.mob_strength_multi = json.get("mob_strength_multi")
                .getAsFloat();

            return config;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
