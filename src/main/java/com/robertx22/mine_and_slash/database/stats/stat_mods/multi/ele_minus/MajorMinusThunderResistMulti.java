package com.robertx22.mine_and_slash.database.stats.stat_mods.multi.ele_minus;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_types.generated.ElementalResist;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public class MajorMinusThunderResistMulti extends BaseMajorEleResistMinus {

    public MajorMinusThunderResistMulti() {
    }

    @Override
    public String GUID() {
        return "MajorMinusThunderResistMulti";
    }

    @Override
    public Stat GetBaseStat() {
        return new ElementalResist(Elements.Thunder);
    }

}