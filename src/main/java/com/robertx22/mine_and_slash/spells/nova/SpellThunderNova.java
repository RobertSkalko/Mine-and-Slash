package com.robertx22.mine_and_slash.spells.nova;

import com.robertx22.mine_and_slash.items.spells.nova.ItemThunderNova;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;

public class SpellThunderNova extends BaseNovaSpell {

    @Override
    public String GUID() {
        return "spell_thunder_nova";
    }

    @Override
    public Elements Element() {
        return Elements.Thunder;
    }

    @Override
    public Item SpellItem() {
        return ItemThunderNova.ITEM;
    }

}
