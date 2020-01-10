package com.robertx22.mine_and_slash.database.items.spell_items.aoe_bomb_proj;

import com.robertx22.mine_and_slash.database.spells.aoe_bomb_proj.SpellAcidBomb;
import com.robertx22.mine_and_slash.database.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ItemAcidBomb extends BaseBombitem {

    @ObjectHolder(Ref.MODID + ":spell_acid_bomb")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
        return SpellAcidBomb.getInstance();
    }

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_acid_bomb";
    }

}
