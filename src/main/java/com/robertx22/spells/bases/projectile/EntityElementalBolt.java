package com.robertx22.spells.bases.projectile;

import com.robertx22.ColoredRedstone;
import com.robertx22.spells.bases.BaseSpellEffect;
import com.robertx22.spells.bases.DamageData;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class EntityElementalBolt extends EntitySpecialThrowable {

    BaseSpellEffect effect;
    DamageData data;

    public abstract Elements element();

    public EntityElementalBolt(World worldIn) {
	super(worldIn);

    }

    public void SetReady(BaseSpellEffect effect, DamageData data) {
	this.effect = effect;
	this.data = data;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
	if (result.entityHit != null && result.entityHit instanceof EntityLivingBase && effect != null
		&& data != null) {

	    effect.Activate(data, (EntityLivingBase) result.entityHit);

	}

	if (!this.world.isRemote) {
	    this.world.setEntityState(this, (byte) 3);
	    this.setDead();
	}

    }

    int ticks = 0;

    @Override
    public void onUpdate() {

	super.onUpdate();

	if (world.isRemote) {

	    ticks++;
	    if (ticks > 1) {
		ticks = 0;
		ColoredRedstone.SpawnAoeRedstone(element(), this, 0.25F, 30);
	    }

	}

	if (this.ticksExisted > 30) {
	    this.setDead();
	}
    }

    public void SpawnAndShoot(BaseSpellEffect effect, DamageData data, EntityLivingBase caster) {

	this.ignoreEntity = caster;
	Vec3d look = caster.getLookVec();

	SetReady(effect, data);
	setPosition(caster.posX + look.x, caster.posY + look.y + 1.3, caster.posZ + look.z);
	shoot(caster, caster.rotationPitch, caster.rotationYaw, 0.0F, 1.5F, 1.0F);

	world.spawnEntity(this);
    }

}