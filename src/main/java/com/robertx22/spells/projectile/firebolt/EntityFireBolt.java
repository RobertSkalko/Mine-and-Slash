package com.robertx22.spells.projectile.firebolt;

import com.robertx22.spells.bases.projectile.EntityElementalBolt;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.world.World;

public class EntityFireBolt extends EntityElementalBolt {

    public EntityFireBolt(World worldIn) {

	super(worldIn);

    }

    @Override
    public Elements element() {
	return Elements.Fire;
    }
}