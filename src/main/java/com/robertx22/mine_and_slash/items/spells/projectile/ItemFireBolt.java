package com.robertx22.mine_and_slash.items.spells.projectile;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.spells.projectile.SpellFireBolt;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ItemFireBolt extends BaseBoltItem {

    public ItemFireBolt() {
        super();
    }

    @ObjectHolder(Ref.MODID + ":spell_firebolt")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
        return new SpellFireBolt();
    }

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_firebolt";
    }

}
