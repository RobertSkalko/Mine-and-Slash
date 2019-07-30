package com.robertx22.mine_and_slash.database.items.spell_items.aoe_projectile;

import com.robertx22.mine_and_slash.database.spells.aoe_projectile.SpellFlameExplosion;
import com.robertx22.mine_and_slash.database.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ItemFlameExplosion extends BaseExplosionItem {

    public ItemFlameExplosion() {
        super();
    }

    @ObjectHolder(Ref.MODID + ":spell_flameexplosion")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
        return new SpellFlameExplosion();
    }

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_flameexplosion";
    }

}
