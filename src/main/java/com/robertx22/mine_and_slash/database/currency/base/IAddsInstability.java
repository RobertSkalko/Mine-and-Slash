package com.robertx22.mine_and_slash.database.currency.base;

import com.robertx22.mine_and_slash.config.forge.ModConfig;

public interface IAddsInstability {

    default boolean addsInstability() {
        return true;
    }

    int instabilityAddAmount();

    default float breakChanceMulti() {
        return 1;
    }

    default float additionalBreakChance() {
        return 0;
    }

    default boolean activatesBreakRoll() {
        return true;
    }

    default boolean canBeUsedAtFullInstability() {
        return ModConfig.INSTANCE.Server.ENABLE_CURRENCY_ITEMS_BREAKING_MODIFIED_ITEMS.get();
    }

}
