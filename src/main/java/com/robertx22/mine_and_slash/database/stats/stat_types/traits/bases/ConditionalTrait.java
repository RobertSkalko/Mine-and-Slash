package com.robertx22.mine_and_slash.database.stats.stat_types.traits.bases;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;

public abstract class ConditionalTrait extends Trait {

    enum MoreOrLess {
        More,
        Less
    }

    public abstract Stat stat();

    public abstract int amount();

    public abstract boolean isPercent();

    public abstract MoreOrLess moreOrLess();

    @Override
    public boolean condition(EntityCap.UnitData unit) {
        if (moreOrLess().equals(MoreOrLess.More)) {
            return unit.getUnit().getStat(stat()).Flat > amount();
        } else {
            return unit.getUnit().getStat(stat()).Flat < amount();
        }
    }

    @Override
    public String locDescForLangFile() {

        String desc = "If " + stat().locNameForLangFile() + " is " + moreOrLess().name() + " than " + amount();

        if (isPercent()) {
            desc += " Percent";
        }

        return desc;

    }

}
