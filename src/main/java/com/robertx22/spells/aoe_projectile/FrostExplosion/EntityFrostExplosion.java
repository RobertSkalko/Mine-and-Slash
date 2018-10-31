package com.robertx22.spells.aoe_projectile.FrostExplosion;

import com.robertx22.spells.bases.projectile.EntityElementalBoltAOE;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.world.World;

public class EntityFrostExplosion extends EntityElementalBoltAOE {

	public EntityFrostExplosion(World worldIn) {

		super(worldIn);

	}

	@Override
	public Elements element() {
		return Elements.Water;
	}

}