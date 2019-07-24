package com.robertx22.mine_and_slash.items.spells.aoe_bomb_proj;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.spells.aoe_bomb_proj.SpellFireBomb;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ItemFireBomb extends BaseBombitem {

    public ItemFireBomb() {
        super();
    }

    @ObjectHolder(Ref.MODID + ":spell_fire_bomb")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
        return new SpellFireBomb();
    }

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_fire_bomb";
    }

}
