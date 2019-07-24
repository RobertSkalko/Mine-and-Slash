package com.robertx22.mine_and_slash.items.spells.self;

import com.robertx22.mine_and_slash.items.spells.BaseSpellItem;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.spells.self.SpellSelfRegen;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ItemSelfRegen extends BaseSpellItem {

    public ItemSelfRegen() {
        super();
    }

    @ObjectHolder(Ref.MODID + ":spell_self_regen")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
        return new SpellSelfRegen();
    }

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_self_regen";
    }

    @Override
    public String locNameForLangFile() {
        return color + "Regenerate";
    }
}
