package com.robertx22.mine_and_slash.database.currency.base;

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
        return !activatesBreakRoll() && instabilityAddAmount() <= 0;
    }

}
