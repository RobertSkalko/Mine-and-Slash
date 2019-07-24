package com.robertx22.mine_and_slash.database.stats.stat_mods.multi.offence;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class PhysicalDamageMulti extends StatMod {

    @Override
    public String GUID() {
	return "PhysicalDamageMulti";
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
	return StatTypes.Multi;
    }

    @Override
    public Stat GetBaseStat() {
	return new PhysicalDamage();
    }

}