package com.robertx22.mine_and_slash.database.stats.mods.flat.resources;

public class HighHealthRegenFlat extends HealthRegenFlat {
    @Override
    public float Min() {
        return super.Min() * 2F;
    }

    @Override
    public float Max() {
        return super.Max() * 2;
    }

}
