package com.robertx22.mine_and_slash.database.stats.mods.multi;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.stats.mods.ElementalStatMod;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalResist;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import com.robertx22.mine_and_slash.uncommon.wrappers.MapWrapper;

public class MajorEleResistMinus extends ElementalStatMod {

    public MajorEleResistMinus(Elements element) {
        super(element);
    }

    @Override
    public float Min() {
        return -20;
    }

    @Override
    public float Max() {
        return -50;
    }

    @Override
    public String GUID() {
        return "major_minus_" + element.guidName + "_resist_multi";
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Multi;
    }

    @Override
    public MapWrapper getBaseStatMap() {
        return ElementalResist.MAP;
    }

    @Override
    public IGUID newGeneratedInstance(Elements element) {
        return new MajorEleResistMinus(element);
    }
}
