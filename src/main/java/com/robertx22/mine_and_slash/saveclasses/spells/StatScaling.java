package com.robertx22.mine_and_slash.saveclasses.spells;

import net.minecraft.util.math.MathHelper;

public enum StatScaling {

    CORE_STAT {
        @Override
        public float scale(float val, int lvl) {
            return val * (1F + (float) lvl / 100);
        }
    },
    NONE {
        @Override
        public float scale(float val, int lvl) {
            return val * lvl;
        }
    },
    NORMAL {
        @Override
        public float scale(float val, int lvl) {
            return val * (float) Math.pow(lvl, getNormalScalingMultiplier(lvl));
        }
    };

    StatScaling() {

    }

    private static float getNormalScalingMultiplier(int lvl) {
        return MathHelper.clamp(0.5F + (float) lvl / 50, 0.5F, 1.25F);
    }

    public abstract float scale(float val, int lvl);
}
