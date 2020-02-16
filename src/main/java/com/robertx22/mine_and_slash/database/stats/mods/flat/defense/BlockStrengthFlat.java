package com.robertx22.mine_and_slash.database.stats.mods.flat.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.defense.BlockStrength;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class BlockStrengthFlat extends StatMod {

    public BlockStrengthFlat() {
    }

    @Override
    public float Min() {
        return 3;
    }

    @Override
    public float Max() {
        return 9F;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return new BlockStrength();
    }

}
