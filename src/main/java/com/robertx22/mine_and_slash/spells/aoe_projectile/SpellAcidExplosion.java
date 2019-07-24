package com.robertx22.mine_and_slash.spells.aoe_projectile;

import com.robertx22.mine_and_slash.items.spells.aoe_projectile.ItemAcidExplosion;
import com.robertx22.mine_and_slash.spells.entities.bases.aoe.EntityAcidExplosion;
import com.robertx22.mine_and_slash.spells.entities.bases.EntityElementalBolt;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellAcidExplosion extends BaseAoeSpellProjectile {

    public SpellAcidExplosion() {
        super();
    }

    @Override
    public Elements Element() {
        return Elements.Nature;
    }

    @Override
    public Item SpellItem() {
        return ItemAcidExplosion.ITEM;
    }

    @Override
    public String GUID() {
        return "AcidExplosion";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
        return new EntityAcidExplosion(world);
    }

}