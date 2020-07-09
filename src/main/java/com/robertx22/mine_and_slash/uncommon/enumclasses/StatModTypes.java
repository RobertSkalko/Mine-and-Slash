package com.robertx22.mine_and_slash.uncommon.enumclasses;

public enum StatModTypes {

    Flat("flat"),
    LOCAL_INCREASE("local_increase"),
    GLOBAL_INCREASE("global_increase");

    StatModTypes(String id) {
        this.id = id;

    }

    public String id;

    public boolean isFlat() {
        return this == Flat;
    }

}
