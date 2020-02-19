package com.robertx22.mine_and_slash.database.runes.base;

import net.minecraft.item.Item;

public class RuneItem extends Item {

    public int rarity;
    public String rune;

    public RuneItem(BaseRune rune) {

        super(new Properties().maxStackSize(1)
            .defaultMaxDamage(0));
        this.rarity = rune.rarity;
        this.rune = rune.GUID();

    }
}
