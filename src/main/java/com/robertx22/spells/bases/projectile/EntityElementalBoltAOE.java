package com.robertx22.spells.bases.projectile;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public abstract class EntityElementalBoltAOE extends EntityElementalBolt {

	public EntityElementalBoltAOE(World worldIn) {
		super(worldIn);

	}

	int radius = 4;
	List<EntityLivingBase> entities = null;

	@Override
	protected void onImpact(RayTraceResult result) {

		if (effect != null && data != null) {

			if (!world.isRemote) {

				double x = result.hitVec.x;
				double y = result.hitVec.y;
				double z = result.hitVec.z;

				entities = world.getEntitiesWithinAABB(EntityLivingBase.class,
						new AxisAlignedBB(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius));

				if (entities != null) {
					for (EntityLivingBase entity : entities) {
						effect.Activate(data, entity);

					}
				}

			}
		}

		if (!this.world.isRemote) {
			this.world.setEntityState(this, (byte) 3);
			this.setDead();
		}

	}
}
