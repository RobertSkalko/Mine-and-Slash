package com.robertx22.mine_and_slash.database.items.spell_items.projectile;

import com.robertx22.mine_and_slash.database.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.projectile.SpellFrostBolt;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ItemFrostBolt extends BaseBoltItem {

    public ItemFrostBolt() {
        super();
    }

    @ObjectHolder(Ref.MODID + ":spell_frostbolt")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
        return new SpellFrostBolt();
    }

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_frostbolt";
    }

}
