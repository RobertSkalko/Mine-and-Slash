package com.robertx22.mine_and_slash.database.stats.mods.flat.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class DodgeRatingFlat extends StatMod {

    public DodgeRatingFlat() {
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
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return DodgeRating.getInstance();
    }

}
