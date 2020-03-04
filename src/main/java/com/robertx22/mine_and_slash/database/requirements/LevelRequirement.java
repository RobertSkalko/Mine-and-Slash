package com.robertx22.mine_and_slash.database.requirements;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.requirements.bases.BaseRequirement;
import com.robertx22.mine_and_slash.database.requirements.bases.GearRequestedFor;
import net.minecraft.util.math.MathHelper;

public class LevelRequirement extends BaseRequirement<LevelRequirement> {

    int minLevel = 0;
    int maxLevel = Integer.MAX_VALUE;

    private LevelRequirement(int minLevel) {
        this.minLevel = minLevel;
    }

    public LevelRequirement() {

    }

    private LevelRequirement(int minLevel, int maxLevel) {
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.add("min_level", new JsonPrimitive(minLevel));
        json.add("max_level", new JsonPrimitive(maxLevel));
        return json;
    }

    @Override
    public LevelRequirement fromJson(JsonObject json) {
        try {
            return new LevelRequirement(json.get("min_level")
                .getAsInt(), json.get("max_level")
                .getAsInt());
        } catch (Exception e) {
            return null;
        }
    }

    public static LevelRequirement lowLVLOnly() {
        return new LevelRequirement(0, 20);
    }

    public static LevelRequirement midLVLOnly() {
        return new LevelRequirement(20, 50);
    }

    public static LevelRequirement highLVLOnly() {
        return new LevelRequirement(50, 75);
    }

    public static LevelRequirement endgameLVLOnly() {
        return new LevelRequirement(75, Integer.MAX_VALUE);
    }

    public static LevelRequirement fromLVL10() {
        return new LevelRequirement(10);
    }

    public static LevelRequirement fromLVL20() {
        return new LevelRequirement(20);
    }

    public static LevelRequirement fromLVL50() {
        return new LevelRequirement(50);
    }

    public static LevelRequirement fromLVL75() {
        return new LevelRequirement(75);
    }

    @Override
    public boolean meetsRequierment(GearRequestedFor requested) {

        int maxPlayerlvl = ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL.get();

        minLevel = MathHelper.clamp(minLevel, 0,
            maxPlayerlvl
        );  // make sure min lvl is not higher than the maximum posible level in case it was decreased by config?
        maxLevel = MathHelper.clamp(maxLevel, 0,
            maxPlayerlvl
        );  // make sure min lvl is not higher than the maximum posible level in case it was decreased by config?

        int level = requested.gearData.level;

        if (level < minLevel || level > maxLevel) {
            return false;
        }

        return true;

    }

    @Override
    public String getJsonID() {
        return "level_req";
    }
}