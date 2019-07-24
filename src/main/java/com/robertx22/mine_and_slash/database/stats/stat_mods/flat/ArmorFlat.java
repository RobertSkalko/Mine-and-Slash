package com.robertx22.mine_and_slash.database.stats.stat_mods.flat;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.defense.Armor;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class ArmorFlat extends StatMod {

    public ArmorFlat() {
    }

    @Override
    public String GUID() {
	return "ArmorFlat";
    }

    @Override
    public float Min() {
	return 3;

    }

    @Override
    public float Max() {
	return 12;
    }

    @Override
    public StatTypes Type() {
	return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
	return new Armor();
    }

}
