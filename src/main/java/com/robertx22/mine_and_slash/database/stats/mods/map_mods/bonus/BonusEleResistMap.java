package com.robertx22.mine_and_slash.database.stats.mods.map_mods.bonus;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.stats.mods.ElementalStatMod;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalResist;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import com.robertx22.mine_and_slash.uncommon.wrappers.MapWrapper;

public class BonusEleResistMap extends ElementalStatMod {

    public BonusEleResistMap(Elements element) {
        super(element);

    }

    @Override
    public MapWrapper getBaseStatMap() {
        return ElementalResist.MAP;
    }

    @Override
    public float Min() {
        return 50;
    }

    @Override
    public float Max() {
        return 100;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public String GUID() {
        return "bonus_" + element.guidName + "_resist_map";
    }

    @Override
    public IGUID newGeneratedInstance(Elements element) {
        return new BonusEleResistMap(element);
    }
}