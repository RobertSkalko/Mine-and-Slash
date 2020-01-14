package com.robertx22.mine_and_slash.database.spells.spell_classes.aoe_projectile;

import com.robertx22.mine_and_slash.database.spells.entities.aoe.EntityLightningExplosion;
import com.robertx22.mine_and_slash.database.spells.entities.bases.EntityElementalBolt;
import com.robertx22.mine_and_slash.database.spells.items.aoe_projectile.ItemLightningExplosion;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellLightningExplosion extends BaseAoeSpellProjectile {

    public SpellLightningExplosion() {
        super();
    }

    @Override
    public Elements getElement() {
        return Elements.Thunder;
    }

    @Override
    public Item getSpellItem() {
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