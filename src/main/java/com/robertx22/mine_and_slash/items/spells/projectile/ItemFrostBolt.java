package com.robertx22.mine_and_slash.items.spells.projectile;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.spells.projectile.SpellFrostBolt;
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
