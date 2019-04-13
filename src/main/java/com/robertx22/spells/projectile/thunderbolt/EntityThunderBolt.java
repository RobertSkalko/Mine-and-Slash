package com.robertx22.spells.projectile.thunderbolt;

import com.robertx22.spells.bases.projectile.EntityElementalBolt;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.world.World;

public class EntityThunderBolt extends EntityElementalBolt {

    public EntityThunderBolt(World worldIn) {

	super(worldIn);

    }

    @Override
    public Elements element() {
	return Elements.Thunder;
    }
}