package com.robertx22.mine_and_slash.database.spells.spell_classes.nova;

import com.robertx22.mine_and_slash.database.spells.items.nova.ItemPoisonNova;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;

public class SpellPoisonNova extends BaseNovaSpell {

    @Override
    public String GUID() {
        return "spell_poison_nova";
    }

    @Override
    public Elements getElement() {
        return Elements.Nature;
    }

    @Override
    public Item getSpellItem() {
        return ItemPoisonNova.ITEM;
    }

}
