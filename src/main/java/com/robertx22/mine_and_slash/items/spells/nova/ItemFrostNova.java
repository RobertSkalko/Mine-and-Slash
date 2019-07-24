package com.robertx22.mine_and_slash.items.spells.nova;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.spells.nova.SpellFrostNova;
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
