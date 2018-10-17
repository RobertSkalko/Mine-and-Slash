package com.robertx22.spells.bases;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public abstract class BaseSpell {

	public abstract String Name();
	// public int manaCost;
	// public int cooldown;

	public BaseSpell() {

	}

	public abstract boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse);

}
