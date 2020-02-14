package com.robertx22.mine_and_slash.database.stats.types.traits.bases;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;

public abstract class BaseTraitLowDodge extends ConditionalTrait {

    @Override
    public Stat stat() {
        return DodgeRating.getInstance();
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
