package com.robertx22.mine_and_slash.items.spells.aoe_bomb_proj;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.spells.aoe_bomb_proj.SpellAcidBomb;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ItemAcidBomb extends BaseBombitem {

    public ItemAcidBomb() {
        super();
    }

    @ObjectHolder(Ref.MODID + ":spell_acid_bomb")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
        return new SpellAcidBomb();
    }

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_acid_bomb";
    }

}
