package com.robertx22.mine_and_slash.saveclasses.spells;

import com.robertx22.mine_and_slash.uncommon.utilityclasses.StatUtils;

public enum StatScaleType {
    NONE {
        @Override
        public float scale(float val, int lvl) {
            return val * lvl;
        }
    },
    NORMAL {
        @Override
        public float scale(float val, int lvl) {
            return StatUtils.calculateNormalScalingStatGrowth(val, lvl);
        }
    };

    StatScaleType() {

    }

    public abstract float scale(float val, int lvl);
}
