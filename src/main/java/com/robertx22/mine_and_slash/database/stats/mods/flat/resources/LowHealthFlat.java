package com.robertx22.mine_and_slash.database.stats.mods.flat.resources;

public class LowHealthFlat extends HealthFlat {
    @Override
    public String GUID() {
        return "low_" + GetBaseStat().GUID() + "_" + this.Type().id;
    }

    @Override
    public float Min() {
        return super.Min() / 3;
    }

    @Override
    public float Max() {
        return super.Max() / 3;
    }

}
