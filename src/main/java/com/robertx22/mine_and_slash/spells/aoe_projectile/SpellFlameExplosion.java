package com.robertx22.mine_and_slash.spells.aoe_projectile;

import com.robertx22.mine_and_slash.items.spells.aoe_projectile.ItemFlameExplosion;
import com.robertx22.mine_and_slash.spells.entities.bases.aoe.EntityFlameExplosion;
import com.robertx22.mine_and_slash.spells.entities.bases.EntityElementalBolt;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellFlameExplosion extends BaseAoeSpellProjectile {

    public SpellFlameExplosion() {
        super();
    }

    @Override
    public Elements Element() {
        return Elements.Fire;
    }

    @Override
    public Item SpellItem() {
        return ItemFlameExplosion.ITEM;
    }

    @Override
    public String GUID() {
        return "FlameExplosion";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
        return new EntityFlameExplosion(world);
    }

}