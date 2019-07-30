package com.robertx22.mine_and_slash.database.spells.nova;

import com.robertx22.mine_and_slash.database.items.spell_items.nova.ItemFireNova;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;

public class SpellFireNova extends BaseNovaSpell {

    @Override
    public String GUID() {
        return "spell_fire_nova";
    }

    @Override
    public Elements Element() {
        return Elements.Fire;
    }

    @Override
    public Item SpellItem() {
        return ItemFireNova.ITEM;
    }

}
