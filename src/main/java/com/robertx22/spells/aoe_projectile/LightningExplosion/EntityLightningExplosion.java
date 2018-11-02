package com.robertx22.spells.aoe_projectile.LightningExplosion;

import com.robertx22.spells.bases.projectile.EntityElementalBoltAOE;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.world.World;

public class EntityLightningExplosion extends EntityElementalBoltAOE {

	public EntityLightningExplosion(World worldIn) {

		super(worldIn);

	}

	@Override
	public Elements element() {
		return Elements.Thunder;
	}

}