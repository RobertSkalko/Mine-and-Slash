package com.robertx22.mine_and_slash.config.forge.parts;

import net.minecraftforge.common.ForgeConfigSpec;

public class StatScaleContainer {

    public StatScaleValue NORMAL_SCALING;
    public StatScaleValue CORE_STAT_SCALING;

    public StatScaleContainer(ForgeConfigSpec.Builder builder) {
        builder.push("STAT SCALING");

        NORMAL_SCALING = builder.comment(
            "val * (float) Math.pow(lvl, MathHelper.clamp(FIRST_VALUE + (float) lvl / " + "SECOND_VALUE, " +
                "THIRD_VALUE, FOURTH_VALUE))")
            .configure((ForgeConfigSpec.Builder b) -> new StatScaleValue(b, "Normal", 0.2D, 50D, 0.2D, 1.25D))
            .getLeft();

        CORE_STAT_SCALING = builder.comment("val * (FIRST_VALUE + (float) lvl / SECOND_VALUE)")
            .configure((ForgeConfigSpec.Builder b) -> new StatScaleValue(b, "Core stat", 1D, 100D, 0D, 0D))
            .getLeft();

        builder.pop();
    }

}
