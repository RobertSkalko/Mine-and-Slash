package com.robertx22.database.stat_mods.flat;

import com.robertx22.database.stat_types.defense.BlockStrength;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class BlockStrengthFlat extends StatMod {

    public BlockStrengthFlat() {
    }

    @Override
    public String GUID() {
	return "BlockStrengthFlat";
    }

    @Override
    public float Min() {
	return 1;
    }

    @Override
    public float Max() {
	return 5;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
	return new BlockStrength();
    }

}
