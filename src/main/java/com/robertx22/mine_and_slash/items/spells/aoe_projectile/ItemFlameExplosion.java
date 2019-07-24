package com.robertx22.mine_and_slash.items.spells.aoe_projectile;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.spells.aoe_projectile.SpellFlameExplosion;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell;
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
