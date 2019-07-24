package com.robertx22.mine_and_slash.uncommon.interfaces.data_items;

import com.robertx22.mine_and_slash.uncommon.localization.Words;

public enum DataItemType {

    GEAR("gear", Words.Gears),
    SPELL("spell", Words.Spell),
    RUNE("rune", Words.Runes),
    MAP("map", Words.Map),
    BLUEPRINT("blueprint", Words.Blueprint);

    DataItemType(String nbtGUID, Words word) {
        this.nbtGUID = nbtGUID;
        this.word = word;
    }

    public Words word;
    public String nbtGUID;
}
