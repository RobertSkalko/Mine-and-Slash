package com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum WeaponTypes {
    None(false, "none"),
    Axe(true, "axe"),
    Hammer(true, "hammer"),
    Bow(true, "bow"),
    Sword(true, "sword"),
    CrossBow(true, "crossbow"),
    Staff(true, "staff"),
    Wand(true, "wand");

    WeaponTypes(boolean bool, String id) {
        this.isSingleType = bool;
        this.id = id;
    }

    boolean isSingleType = true;
    public String id;

    public static List<WeaponTypes> getAll() {

        return Arrays.stream(WeaponTypes.values())
                .filter(x -> x.isSingleType)
                .collect(Collectors.toList());

    }
}
