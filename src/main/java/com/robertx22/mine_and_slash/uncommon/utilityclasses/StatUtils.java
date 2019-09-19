package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import net.minecraft.util.math.MathHelper;

public class StatUtils {

    public static float calculateBaseStatScalingStatGrowth(float stat, int lvl) {
        return stat * lvl;
    }

    public static float calculateNormalScalingStatGrowth(float stat, int lvl) {
        return stat * (float) Math.pow(lvl, getNormalScalingMultiplier(lvl));
    }

    private static float getNormalScalingMultiplier(int lvl) {
        return MathHelper.clamp(0.5F + (float) lvl / 50, 0.5F, 1.5F);
    }

    public static float roundNumber(float n) {

        if (n < 10) {

        } else if (n < 100) {
            n = ((int) n / 2) * 2;
        } else if (n < 1000) {
            n = ((int) n / 50) * 50;
        } else if (n < 10000) {
            n = ((int) n / 500) * 500;
        } else if (n < 100000) {
            n = ((int) n / 1000) * 1000;
        } else {
            n = ((int) n / 5000) * 5000;
        }
        return n;

    }

}
