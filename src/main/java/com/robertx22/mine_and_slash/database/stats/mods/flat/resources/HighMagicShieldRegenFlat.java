package com.robertx22.mine_and_slash.database.stats.mods.flat.resources;

public class HighMagicShieldRegenFlat extends MagicShieldRegenFlat {

    @Override
    public String GUID() {
        return "high_magic_shield_regen_flat";
    }

    @Override
    public float Min() {
        return super.Min() * 2;
    }

    @Override
    public float Max() {
        return super.Max() * 2;
    }

}
