package com.robertx22.spells;

import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityElementalBolt extends EntityThrowable {

	public EntityElementalBolt(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (result.entityHit != null) {

			result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float) 5);

		}

		if (!this.world.isRemote) {
			this.world.setEntityState(this, (byte) 3);
			this.setDead();
		}

	}

}