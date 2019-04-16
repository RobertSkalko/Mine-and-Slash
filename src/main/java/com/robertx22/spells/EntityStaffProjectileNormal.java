package com.robertx22.spells;

import com.robertx22.spells.bases.projectile.EntityStaffProjectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityStaffProjectileNormal extends EntityStaffProjectile {

    public EntityStaffProjectileNormal(World worldIn) {
	super(worldIn);

    }

    public EntityStaffProjectileNormal(World worldIn, EntityLivingBase thrower) {
	super(worldIn);
	this.thrower = thrower;

    }

}
