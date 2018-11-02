package com.robertx22.spells.aoe_projectile.FlameExplosion;

import com.robertx22.spells.bases.projectile.EntityElementalBoltAOE;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.world.World;

public class EntityFlameExplosion extends EntityElementalBoltAOE {

	public EntityFlameExplosion(World worldIn) {

		super(worldIn);

	}

	@Override
	public Elements element() {
		return Elements.Fire;
	}

}