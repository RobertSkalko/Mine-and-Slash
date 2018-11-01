package com.robertx22.mmorpg.registers;

import com.robertx22.spells.aoe_projectile.FrostExplosion.EntityFrostExplosion;
import com.robertx22.spells.projectile.acidbolt.EntityAcidBolt;
import com.robertx22.spells.projectile.firebolt.EntityFireBolt;
import com.robertx22.spells.projectile.frostbolt.EntityFrostBolt;
import com.robertx22.spells.projectile.thunderbolt.EntityThunderBolt;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.init.Items;

public class EntityRegisters {

	public static void Register() {

		RegisterUtils.RegisterModEntity(Items.SNOWBALL, EntityFrostBolt.class);
		RegisterUtils.RegisterModEntity(Items.SNOWBALL, EntityFrostExplosion.class);
		RegisterUtils.RegisterModEntity(Items.MAGMA_CREAM, EntityFireBolt.class);
		RegisterUtils.RegisterModEntity(Items.SLIME_BALL, EntityAcidBolt.class);
		RegisterUtils.RegisterModEntity(Items.GLOWSTONE_DUST, EntityThunderBolt.class);
	}
}
