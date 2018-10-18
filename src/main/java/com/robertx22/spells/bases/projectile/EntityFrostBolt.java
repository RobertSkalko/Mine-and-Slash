package com.robertx22.spells.bases.projectile;

import com.robertx22.datasaving.DamageSaving;
import com.robertx22.saveclasses.Unit;
import com.robertx22.spells.bases.IDamageSource;
import com.robertx22.spells.bases.MyDamageSource;

import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityFrostBolt extends EntitySnowball implements IDamageSource {
	public EntityFrostBolt(World par1World) {
		super(par1World);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (result.entityHit != null) {

			Unit source = DamageSaving.Load(this);

			if (source != null) {

				System.out.println("CAPABILITY TO PROJECTILE WORKS!");
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