package com.robertx22.mine_and_slash.database.stats.mods.flat.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class HighArmorFlat extends StatMod {

    public HighArmorFlat() {
    }

    @Override
    public String GUID() {
        return "high_armor_flat";
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
        return Armor.getInstance();
    }

}
