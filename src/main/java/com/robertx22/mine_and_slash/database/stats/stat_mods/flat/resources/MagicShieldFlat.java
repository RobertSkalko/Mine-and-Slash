package com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.MagicShield;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class MagicShieldFlat extends StatMod {

    public MagicShieldFlat() {

    }

    @Override
    public String GUID() {
        return "magic_shield_flat";
    }

    @Override
    public float Min() {
        return 5;
    }

    @Override
    public float Max() {
        return 15;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return MagicShield.INSTANCE;
    }

}

