package com.robertx22.mine_and_slash.spells.nova;

import com.robertx22.mine_and_slash.items.spells.nova.ItemFrostNova;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;

public class SpellFrostNova extends BaseNovaSpell {

    @Override
    public String GUID() {
        return "spell_frost_nova";
    }

    @Override
    public Elements Element() {
        return Elements.Water;
    }

    @Override
    public Item SpellItem() {
        return ItemFrostNova.ITEM;
    }

}
