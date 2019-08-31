package com.robertx22.mine_and_slash.database.items.currency;

public interface IAddsInstability {

    default boolean addsInstability() {
        return true;
    }

    int instabilityAddAmount();

    default boolean activatesBreakRoll() {
        return true;
    }

}
