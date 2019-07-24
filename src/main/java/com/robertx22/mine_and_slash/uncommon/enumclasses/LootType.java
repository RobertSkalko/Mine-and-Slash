package com.robertx22.mine_and_slash.uncommon.enumclasses;

import com.robertx22.mine_and_slash.database.IGUID;

public enum LootType implements IGUID {

    NormalItem("Normal Item"), UniqueItem("Unique Item"), LootBox("Loot Box"), AwakenRuneWord("Awaken Runeword"), RunedItem("Runed Item"), Currency("Currency"), CompatibleItem("Compatible Item"), Map("Map"), Spell("Spell"), Rune("Rune"), All("All");

    private LootType(String name) {
        this.thename = name;
    }

    private String thename;

    public String getName() {
        return thename;
    }

    @Override
    public String GUID() {
        return thename;
    }
}
