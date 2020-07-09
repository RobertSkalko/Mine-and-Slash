package com.robertx22.mine_and_slash.uncommon.enumclasses;

import java.util.Locale;

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

    public static StatModTypes fromString(String str) {

        for (StatModTypes type : StatModTypes.values()) {
            if (type.id.toLowerCase(Locale.ROOT)
                .equals(str.toLowerCase(Locale.ROOT))) {
                return type;
            }
        }

        return valueOf(str);

    }

}
