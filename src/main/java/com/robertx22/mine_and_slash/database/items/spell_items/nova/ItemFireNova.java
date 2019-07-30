package com.robertx22.mine_and_slash.database.items.spell_items.nova;

import com.robertx22.mine_and_slash.database.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.nova.SpellFireNova;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ItemFireNova extends BaseItemNova {

    public ItemFireNova() {
        super();

    }

    @ObjectHolder(Ref.MODID + ":spell_fire_nova")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
        return new SpellFireNova();
    }

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_fire_nova";
    }

}
