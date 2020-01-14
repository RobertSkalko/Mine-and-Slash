package com.robertx22.mine_and_slash.database.spells.spell_classes.aoe_projectile;

import com.robertx22.mine_and_slash.database.spells.entities.aoe.EntityFlameExplosion;
import com.robertx22.mine_and_slash.database.spells.entities.bases.EntityElementalBolt;
import com.robertx22.mine_and_slash.database.spells.items.aoe_projectile.ItemFlameExplosion;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellFlameExplosion extends BaseAoeSpellProjectile {

    public SpellFlameExplosion() {
        super();
    }

    @Override
    public Elements getElement() {
        return Elements.Fire;
    }

    @Override
    public Item getSpellItem() {
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