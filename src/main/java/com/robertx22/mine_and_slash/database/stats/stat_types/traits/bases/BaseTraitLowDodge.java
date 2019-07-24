package com.robertx22.mine_and_slash.database.stats.stat_types.traits.bases;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_types.defense.Dodge;

public abstract class BaseTraitLowDodge extends ConditionalTrait {

    @Override
    public Stat stat() {
        return new Dodge();
    }

    @Override
    public int amount() {
        return 5;
    }

    @Override
    public boolean isPercent() {
        return true;
    }

    @Override
    public MoreOrLess moreOrLess() {
        return MoreOrLess.Less;
    }

}
