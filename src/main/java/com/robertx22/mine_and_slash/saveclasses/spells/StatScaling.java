package com.robertx22.mine_and_slash.saveclasses.spells;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.config.forge.parts.StatScaleValue;
import net.minecraft.util.math.MathHelper;

public enum StatScaling {

    CORE_STAT {
        @Override
        public float scale(float val, int lvl) {

            StatScaleValue config = ModConfig.INSTANCE.StatScaling.CORE_STAT_SCALING;

            float FIRST_VALUE = config.FIRST_VALUE.get()
                .floatValue();
            float SECOND_VALUE = config.SECOND_VALUE.get()
                .floatValue();

            return val * (FIRST_VALUE + (float) lvl / SECOND_VALUE);
        }
    },
    NONE {
        @Override
        public float scale(float val, int lvl) {
            return val;
        }
    },
    NORMAL {
        @Override
        public float scale(float val, int lvl) {

            StatScaleValue config = ModConfig.INSTANCE.StatScaling.NORMAL_SCALING;

            float FIRST_VALUE = config.FIRST_VALUE.get()
                .floatValue();
            float SECOND_VALUE = config.SECOND_VALUE.get()
                .floatValue();
            float THIRD_VALUE = config.THIRD_VALUE.get()
                .floatValue();
            float FOURTH_VALUE = config.FOURTH_VALUE.get()
                .floatValue();

            return val * (float) Math.pow(
                lvl, MathHelper.clamp(FIRST_VALUE + (float) lvl / SECOND_VALUE, THIRD_VALUE, FOURTH_VALUE));

        }
    };

    StatScaling() {

    }

    public abstract float scale(float val, int lvl);
}
