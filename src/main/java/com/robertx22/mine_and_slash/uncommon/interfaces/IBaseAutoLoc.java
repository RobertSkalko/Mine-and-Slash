package com.robertx22.mine_and_slash.uncommon.interfaces;

import com.robertx22.mine_and_slash.database.IGUID;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;

public interface IBaseAutoLoc extends IGUID {

    enum AutoLocGroup {
        Runes,
        Unique_Items,
        Spells,
        Gear_Items,
        Words,
        Rarities,
        Prefixes,
        Suffixes,
        Rune_Words,
        Item_Sets,
        Stats,
        Misc,
        Gear_Slots,
        World_Types,
        Lootboxes,
        Chat_Messages,
        Configs,
        Currency_Items,
        Advancement_titles,
        Advancement_descriptions,
        Potions

    }

    default String getPrefix() {
        if (this instanceof Item) {
            return "item.";
        } else if (this instanceof Block) {
            return "block.";
        } else if (this instanceof Effect) {
            return "effect.";
        } else {
            return "";
        }

    }

}
