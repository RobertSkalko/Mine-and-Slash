package com.robertx22.mine_and_slash.database.spells.spell_classes.nova;

import com.robertx22.mine_and_slash.database.spells.items.nova.ItemFrostNova;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;

public class SpellFrostNova extends BaseNovaSpell {

    @Override
    public String GUID() {
        return "spell_frost_nova";
    }

    @Override
    public Elements getElement() {
        return Elements.Water;
    }

    @Override
    public Item getSpellItem() {
        return ItemFrostNova.ITEM;
    }

}
