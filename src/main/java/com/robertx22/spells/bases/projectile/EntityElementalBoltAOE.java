package com.robertx22.spells.bases.projectile;

import java.util.List;

import com.robertx22.ColoredRedstone;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public abstract class EntityElementalBoltAOE extends EntityElementalBolt {

    public EntityElementalBoltAOE(World worldIn) {
	super(worldIn);

    }

    public static int radius = 3;

    @Override
    protected void onImpact(RayTraceResult result) {

	if (world.isRemote) {

	    world.playSound(this.posX, this.posY, this.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS,
		    0.1F, 0.5F, true);

	} else {

	    ColoredRedstone.SpawnAoeRedstone(element(), this, radius, 100);

	}
	if (effect != null && data != null) {

	    double x = result.hitVec.x;
	    double y = result.hitVec.y;
	    double z = result.hitVec.z;

	    List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class,
		    new AxisAlignedBB(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius));

	    if (entities != null) {
		for (EntityLivingBase entity : entities) {
		    effect.Activate(data, entity);
		}
	    }
	}

	if (!this.world.isRemote) {
	    this.world.setEntityState(this, (byte) 3);
	    this.setDead();
	}

    }
}
