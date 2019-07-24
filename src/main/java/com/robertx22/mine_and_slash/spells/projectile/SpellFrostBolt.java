package com.robertx22.mine_and_slash.spells.projectile;

import com.robertx22.mine_and_slash.items.spells.projectile.ItemFrostBolt;
import com.robertx22.mine_and_slash.spells.entities.bases.proj.EntityFrostBolt;
import com.robertx22.mine_and_slash.spells.entities.bases.EntityElementalBolt;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellFrostBolt extends BaseSpellProjectile {

    public SpellFrostBolt() {
        super();
    }

    @Override
    public Elements Element() {
        return Elements.Water;
    }

    @Override
    public Item SpellItem() {
        return ItemFrostBolt.ITEM;
    }

    @Override
    public String GUID() {
        return "FrostBolt";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
        return new EntityFrostBolt(world);
    }

}