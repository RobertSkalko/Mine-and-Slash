package com.robertx22.mine_and_slash.database.stats;

import net.minecraft.util.math.MathHelper;

public interface IUsableStat {

    /**
     * 0.75 means 75% will be maximum value
     */
    public float MaximumPercent();

    /**
     * Used to get usable value. So 5000 armor turns into 50% armor reduction
     */
    public float AverageStat();

    public default float GetUsableValue(int Level, int value) {

        if (this instanceof Stat) {
            Stat stat = (Stat) this;

            float AvgStat = this.AverageStat();

            float number = (float) value / stat.calculateScalingStatGrowth(AvgStat, Level);

            if (number < 0) {
                number = 0;
            }

            float finalval = (float) (MaximumPercent() * (float) number / ((float) number + (float) 10));

            return MathHelper.clamp(finalval, 0, MaximumPercent());
        }
        return 0;
    }
}
