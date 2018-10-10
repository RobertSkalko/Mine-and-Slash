package com.robertx22.constants;

import com.robertx22.customitems.MyItems;

import net.minecraft.item.Item;
import net.minecraft.util.text.TextFormatting;

public class Gear {

    public static TextFormatting[] rarityChatColors = {
            TextFormatting.YELLOW,
            TextFormatting.GREEN,
            TextFormatting.BLUE,
            TextFormatting.GOLD,
            TextFormatting.LIGHT_PURPLE
    };

    public static final Item[] magic_weapons = {
            MyItems.magic_sword,
    };
    public static final Item[] rare_weapons = {
            MyItems.rare_sword,
    };
    public static final Item[] epic_weapons = {
            MyItems.epic_sword,
    };
    public static final Item[] legendary_weapons = {
            MyItems.legendary_sword,
    };
    public static final Item[] mythical_weapons = {
            MyItems.mythical_sword,
    };

    public static final Item[] magic_armors = {
            MyItems.magic_leggins,
            MyItems.magic_chestplate,
            MyItems.magic_boots,
            MyItems.magic_helmet
    };

    public static final Item[] rare_armors = {
            MyItems.rare_leggins,
            MyItems.rare_chestplate,
            MyItems.rare_boots,
            MyItems.rare_helmet
    };
    public static final Item[] epic_armors = {
            MyItems.epic_leggins,
            MyItems.epic_chestplate,
            MyItems.epic_boots,
            MyItems.epic_helmet
    };
    public static final Item[] legendary_armors = {
            MyItems.legendary_leggins,
            MyItems.legendary_chestplate,
            MyItems.legendary_boots,
            MyItems.legendary_helmet
    };
    public static final Item[] mythical_armors = {
            MyItems.mythical_leggins,
            MyItems.mythical_chestplate,
            MyItems.mythical_boots,
            MyItems.mythical_helmet
    };

    public static final String[] rarityNames = {
            "Magic",
            "Rare",
            "Epic",
            "Legendary",
            "Mythical"};

}
