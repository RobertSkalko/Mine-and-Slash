package com.robertx22.mine_and_slash.database.stats;

import com.robertx22.mine_and_slash.database.stats.types.BaseTrait;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsOtherStats;

public abstract class Trait extends BaseTrait implements IAffectsOtherStats {

    @Override
    public String locDescForLangFile() {
        return "";
    }

    @Override
    public void TryAffectOtherStats(UnitData unit, StatData data) {
        if (this.condition(unit)) {
            for (StatModData mod : getStatsMods()) {
                mod.useOnPlayer(unit, unit.getLevel());
            }
        }

    }

    // override if it has a condition
    public boolean condition(UnitData unit) {
        return true;
    }

    @Override
    public Elements getElement() {
        return null;
    }

}
