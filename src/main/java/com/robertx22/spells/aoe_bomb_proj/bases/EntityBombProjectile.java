package com.robertx22.spells.aoe_bomb_proj.bases;

import java.util.List;

import com.robertx22.ColoredRedstone;
import com.robertx22.SoundUtils;
import com.robertx22.spells.bases.projectile.EntityElementalBolt;
import com.robertx22.spells.bases.projectile.Targeting;
import com.robertx22.uncommon.capability.EntityData;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public abstract class EntityBombProjectile extends EntityElementalBolt {

    float maxSize = 3;
    float sizeIncrement = 0.05F;

    public EntityBombProjectile(World worldIn) {
	super(worldIn);

	this.setDeathTime(60);
	this.setAirProcTime(40);
	this.setDoExpireProc(true);

    }

    @Override
    public void onUpdate() {

	super.onUpdate();

    }

    @Override
    protected boolean onExpireProc(EntityLivingBase caster) {
	return doEffect(caster);
    }

    public boolean doEffect(EntityLivingBase caster) {

	if (world.isRemote) {

	    SoundUtils.playSound(this, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.6F, 0.7F);

	} else {

	    ColoredRedstone.SpawnAoeRedstone(element(), this, 2, 30);

	}

	this.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.posX, this.posY, this.posZ, 1, 1, 1, 0);

	if (!this.world.isRemote && caster != null && effect != null) {

	    List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this.thrower,
		    this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(2.5D));

	    for (int i = 0; i < list.size(); ++i) {
		Entity entity1 = list.get(i);

		boolean hit = false;
		if (entity1.hasCapability(EntityData.Data, null)) {
		    effect.Activate(data, (EntityLivingBase) entity1);
		    hit = true;
		}
		return hit;

	    }
	}
	return false;

    }

    @Override
    protected Targeting.TargetType getTargetType() {
	return Targeting.TargetType.ENEMY;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
	if (!this.world.isRemote) {

	    switch (result.typeOfHit) {
	    case BLOCK:
		this.motionX = 0.0;
		this.motionY = 0.0;
		this.motionZ = 0.0;
		break;
	    case ENTITY:
		break;
	    case MISS:
		break;
	    }
	}

    }

    @Override
    public float getGravityVelocity() {
	return 0.00F;
    }

}