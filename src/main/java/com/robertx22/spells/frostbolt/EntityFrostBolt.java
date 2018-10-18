package com.robertx22.spells.frostbolt;

import com.robertx22.spells.bases.DamageData;
import com.robertx22.spells.bases.IDamageSource;
import com.robertx22.spells.bases.IEntityDamageSource;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityFrostBolt extends EntitySnowball implements IDamageSource, IEntityDamageSource {
	public EntityFrostBolt(World par1World) {
		super(par1World);
	}

	DamageData data = null;

	@Override
	protected void onImpact(RayTraceResult result) {
		if (result.entityHit != null && result.entityHit instanceof EntityLivingBase) {

			if (data != null) {

				System.out.println("interface TO PROJECTILE WORKS!");

				data.effect.Activate(data, (EntityLivingBase) result.entityHit);

			} else {
				System.out.println("doesnt work!");

			}
		}

		if (!this.world.isRemote) {
			this.world.setEntityState(this, (byte) 3);
			this.setDead();
		}
	}

	@Override
	public void SetData(DamageData data) {
		this.data = data;

	}

	@Override
	public DamageData GetData() {
		return data;
	}
}