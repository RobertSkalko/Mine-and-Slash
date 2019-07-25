package com.robertx22.mine_and_slash.uncommon.interfaces.data_items;

import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.RuneItemData;
import com.robertx22.mine_and_slash.uncommon.localization.Words;

public enum DataItemType {

    GEAR("gear", Words.Gears) {
        @Override
        public boolean isType(ICommonDataItem data) {
            return data instanceof GearItemData;
        }
    },
    SPELL("spell", Words.Spell) {
        @Override
        public boolean isType(ICommonDataItem data) {
            return data instanceof GearItemData;
        }
    },
    RUNE("rune", Words.Runes) {
        @Override
        public boolean isType(ICommonDataItem data) {
            return data instanceof RuneItemData;
        }
    },
    MAP("map", Words.Map) {
        @Override
        public boolean isType(ICommonDataItem data) {
            return data instanceof MapItemData;
        }
    },
    BLUEPRINT("blueprint", Words.Blueprint) {
        @Override
        public boolean isType(ICommonDataItem data) {
            return false;
        }
    };

    DataItemType(String nbtGUID, Words word) {
        this.nbtGUID = nbtGUID;
        this.word = word;
    }

    public abstract boolean isType(ICommonDataItem data);

    public Words word;
    public String nbtGUID;
}
