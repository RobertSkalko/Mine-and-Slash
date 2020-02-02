package com.robertx22.mine_and_slash.config.forge.parts;

import net.minecraftforge.common.ForgeConfigSpec;

public class StatScaleValue {

    public ForgeConfigSpec.DoubleValue FIRST_VALUE;
    public ForgeConfigSpec.DoubleValue SECOND_VALUE;
    public ForgeConfigSpec.DoubleValue THIRD_VALUE;
    public ForgeConfigSpec.DoubleValue FOURTH_VALUE;

    StatScaleValue(ForgeConfigSpec.Builder builder, String name, Double first, Double second, Double third,
                   Double fourth) {
        builder.push(name);

        float min = -1000;
        float max = 1000;

        FIRST_VALUE = builder.comment(".").defineInRange("FIRST_VALUE", first, min, max);
        SECOND_VALUE = builder.comment(".").defineInRange("SECOND_VALUE", second, min, max);
        THIRD_VALUE = builder.comment(".").defineInRange("THIRD_VALUE", third, min, max);
        FOURTH_VALUE = builder.comment(".").defineInRange("FOURTH_VALUE", fourth, min, max);

        builder.pop();
    }

}
