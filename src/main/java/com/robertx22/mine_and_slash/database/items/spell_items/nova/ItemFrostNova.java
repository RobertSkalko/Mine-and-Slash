package com.robertx22.mine_and_slash.database.items.spell_items.nova;

import com.robertx22.mine_and_slash.database.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.nova.SpellFrostNova;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ItemFrostNova extends BaseItemNova {

    public ItemFrostNova() {
        super();
    }

    @ObjectHolder(Ref.MODID + ":spell_frost_nova")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
        return new SpellFrostNova();
    }

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_frost_nova";
    }

}
