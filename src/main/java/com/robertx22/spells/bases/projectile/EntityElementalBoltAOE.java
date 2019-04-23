package com.robertx22.spells.bases.projectile;

import java.util.List;

import com.robertx22.ColoredRedstone;
import com.robertx22.uncommon.utilityclasses.SoundUtils;
import com.robertx22.uncommon.utilityclasses.WizardryUtilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public abstract class EntityElementalBoltAOE extends EntityElementalBolt {

    public EntityElementalBoltAOE(World worldIn) {
	super(worldIn);

    }

    @Override
    public double radius() {
	return 3D;
    }

    @Override
    protected void onImpact(RayTraceResult result) {

	if (world.isRemote) {

	    SoundUtils.playSound(this, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.4F, 0.5F);

	} else {

	    ColoredRedstone.SpawnAoeRedstone(element(), this, radius(), 500);

	}
	if (effect != null && data != null) {

	    List<EntityLivingBase> entities = WizardryUtilities.getEntitiesWithinRadius(radius(), this,
		    EntityLivingBase.class);

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
