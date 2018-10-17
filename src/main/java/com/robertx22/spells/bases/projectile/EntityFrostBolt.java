package com.robertx22.spells.bases.projectile;

import com.robertx22.spells.bases.MyDamageSource;

import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityFrostBolt extends EntitySnowball {
	public EntityFrostBolt(World par1World) {
		super(par1World);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (result.entityHit != null) {
			int i = 0;

			if (result.entityHit instanceof EntityBlaze) {
				i = 3;
			}

			MyDamageSource dmg = new MyDamageSource("test");

			result.entityHit.attackEntityFrom(dmg, 5);

		}

		if (!this.world.isRemote) {
			this.world.setEntityState(this, (byte) 3);
			this.setDead();
		}
	}
}