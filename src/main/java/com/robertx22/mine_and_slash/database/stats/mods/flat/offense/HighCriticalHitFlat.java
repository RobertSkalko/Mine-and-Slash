package com.robertx22.mine_and_slash.database.stats.mods.flat.offense;

public class HighCriticalHitFlat extends CriticalHitFlat {
    @Override
    public float Min() {
        return super.Min() * 2F;

    }

    @Override
    public float Max() {
        return super.Max() * 2;
    }

    @Override
    public String GUID() {
        return "high_critical_hit_flat";
    }

}
