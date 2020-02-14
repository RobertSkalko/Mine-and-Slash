package com.robertx22.mine_and_slash.database.stats.mods.flat;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class MajorDodgeFlat extends StatMod {

    public MajorDodgeFlat() {
    }

    @Override
    public String GUID() {
        return "major_dodge_flat";
    }

    @Override
    public float Min() {
        return 3;
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
        return DodgeRating.getInstance();
    }

}
