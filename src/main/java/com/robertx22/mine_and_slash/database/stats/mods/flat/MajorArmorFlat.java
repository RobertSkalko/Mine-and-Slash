package com.robertx22.mine_and_slash.database.stats.mods.flat;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class MajorArmorFlat extends StatMod {

    public MajorArmorFlat() {
    }

    @Override
    public String GUID() {
        return "MajorArmorFlat";
    }

    @Override
    public float Min() {
        return 6;
    }

    @Override
    public float Max() {
        return 25;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return Armor.INSTANCE;
    }

}
