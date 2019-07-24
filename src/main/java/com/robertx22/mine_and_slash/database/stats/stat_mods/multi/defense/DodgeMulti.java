package com.robertx22.mine_and_slash.database.stats.stat_mods.multi.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.defense.Dodge;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class DodgeMulti extends StatMod {

    @Override
    public String GUID() {
	return "DodgeMulti";
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
	return StatTypes.Multi;
    }

    @Override
    public Stat GetBaseStat() {
	return new Dodge();
    }

}
