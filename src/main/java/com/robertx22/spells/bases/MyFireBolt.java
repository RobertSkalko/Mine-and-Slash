package com.robertx22.spells.bases;

import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.world.World;

public class MyFireBolt extends EntitySmallFireball implements IDamageSource {

	public MyFireBolt(World world) {
		super(world);
	}
}
