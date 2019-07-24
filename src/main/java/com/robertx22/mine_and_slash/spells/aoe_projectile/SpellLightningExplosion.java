package com.robertx22.mine_and_slash.spells.aoe_projectile;

import com.robertx22.mine_and_slash.items.spells.aoe_projectile.ItemLightningExplosion;
import com.robertx22.mine_and_slash.spells.entities.bases.EntityElementalBolt;
import com.robertx22.mine_and_slash.spells.entities.bases.aoe.EntityLightningExplosion;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellLightningExplosion extends BaseAoeSpellProjectile {

    public SpellLightningExplosion() {
        super();
    }

    @Override
    public Elements Element() {
        return Elements.Thunder;
    }

    @Override
    public Item SpellItem() {
        return ItemLightningExplosion.ITEM;
    }

    @Override
    public String GUID() {
        return "LightningExplosion";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
        return new EntityLightningExplosion(world);
    }

}