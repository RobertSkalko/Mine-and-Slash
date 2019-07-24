package com.robertx22.mine_and_slash.database.requirements;

import com.robertx22.mine_and_slash.config.ModConfig;
import net.minecraft.util.math.MathHelper;

public class LevelRequirement extends BaseRequirement {

    int minLevel = 0;
    int maxLevel = Integer.MAX_VALUE;

    private LevelRequirement(int minLevel) {
        this.minLevel = minLevel;
    }

    private LevelRequirement(int minLevel, int maxLevel) {
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
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
        return new LevelRequirement(75, 100);
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

        minLevel = MathHelper.clamp(minLevel, 0, maxPlayerlvl);  // make sure min lvl is not higher than the maximum posible level in case it was decreased by config?
        maxLevel = MathHelper.clamp(maxLevel, 0, maxPlayerlvl);  // make sure min lvl is not higher than the maximum posible level in case it was decreased by config?

        int level = requested.gearData.level;

        if (level < minLevel || level > maxLevel) {
            return false;
        }

        return true;

    }

}