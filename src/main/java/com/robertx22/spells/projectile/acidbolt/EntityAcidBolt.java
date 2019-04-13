package com.robertx22.spells.projectile.acidbolt;

import com.robertx22.spells.bases.projectile.EntityElementalBolt;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.world.World;

public class EntityAcidBolt extends EntityElementalBolt {

    public EntityAcidBolt(World worldIn) {

	super(worldIn);

    }

    @Override
    public Elements element() {
	return Elements.Nature;
    }

}