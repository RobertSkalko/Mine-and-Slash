package com.robertx22.mine_and_slash.uncommon.enumclasses;

import com.robertx22.mine_and_slash.database.IGUID;

public enum LootType implements IGUID {

    NormalItem("Normal Item", "normal_item"),
    UniqueItem("Unique Item", "unique_item"),
    LootBox("Loot Box", "loot_box"),
    AwakenRuneWord("Awaken Runeword", "awaken_runeword"),
    RunedItem("Runed Item", "runed_item"),
    Currency("Currency", "currency"),
    CompatibleItem("Compatible Item", "compatible_item"),
    Map("Map", "map"),
    Rune("Rune", "rune"),
    UniqueRune("Unique Rune", "unique_rune"),
    All("All", "all"),
    Blueprint("Blueprint", "blueprint");

    private LootType(String name, String id) {
        this.thename = name;
        this.id = id;
    }

    String id;
    private String thename;

    public String getName() {
        return thename;
    }

    @Override
    public String GUID() {
        return id;
    }
}
