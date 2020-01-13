package com.robertx22.mine_and_slash.database.spells.items.aoe_bomb_proj;

import com.robertx22.mine_and_slash.database.spells.spell_classes.aoe_bomb_proj.SpellIceBomb;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ItemIceBomb extends BaseBombitem {

    public ItemIceBomb() {
        super();
    }

    @ObjectHolder(Ref.MODID + ":spell_ice_bomb")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
        return new SpellIceBomb();
    }

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_ice_bomb";
    }

}
