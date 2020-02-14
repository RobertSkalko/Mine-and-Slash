package com.robertx22.mine_and_slash.database.stats.mods.flat;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class DodgeRatingFlat extends StatMod {

    public DodgeRatingFlat() {
    }

    @Override
    public String GUID() {
        return "dodge_flat";
    }

    @Override
    public float Min() {
        return 5;
    }

    @Override
    public float Max() {
        return 10;
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
