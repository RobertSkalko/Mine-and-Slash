package com.robertx22.mine_and_slash.database.items.spell_items.self;

import com.robertx22.mine_and_slash.database.items.spell_items.BaseSpellItem;
import com.robertx22.mine_and_slash.database.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.self.SpellInstantHeal;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ItemInstantHeal extends BaseSpellItem {

    public ItemInstantHeal() {
        super();
    }

    @ObjectHolder(Ref.MODID + ":spell_instantheal")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
        return new SpellInstantHeal();
    }

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_instantheal";
    }

    @Override
    public String locNameForLangFile() {
        return color + "Instant Heal";
    }
}


