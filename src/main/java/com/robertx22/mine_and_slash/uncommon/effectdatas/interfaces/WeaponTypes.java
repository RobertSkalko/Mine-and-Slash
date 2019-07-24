package com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum WeaponTypes {
    None(false),
    Axe(true),
    Hammer(true),
    Bow(true),
    Sword(true),
    Staff(true);

    WeaponTypes(boolean bool) {
        this.isSingleType = bool;
    }

    boolean isSingleType = true;

    public static List<WeaponTypes> getAll() {

        return Arrays.stream(WeaponTypes.values())
                .filter(x -> x.isSingleType)
                .collect(Collectors.toList());

    }
}
