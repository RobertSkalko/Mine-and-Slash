package com.robertx22.mine_and_slash.loot.blueprints.bases;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.util.math.MathHelper;

public class LevelPart extends BlueprintPart<Integer> {

    public boolean LevelRange = true;

    public int LevelVariance = 3;

    public int minLevel = 1;

    public int maxLevel = Integer.MAX_VALUE;

    public int startPointLvl;

    @Override
    protected Integer generateIfNull() {

        int finalLvl = startPointLvl;

        if (LevelRange) {

            finalLvl = RandomUtils.RandomRange(startPointLvl - LevelVariance, startPointLvl + LevelVariance);

        }
        return MathHelper.clamp(finalLvl, this.minLevel, ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL
                .get());
    }
}


