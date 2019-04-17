package com.robertx22.spells.bases.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class Targeting {
    public enum TargetType {
	ALLY, ENEMY, ALL
    }

    public static boolean isValidTarget(TargetType targetType, EntityLivingBase thrower, Entity entity,
	    boolean shouldExcludeCaster) {

	if (targetType.equals(TargetType.ENEMY) && thrower != entity) {
	    return true;
	} else if (targetType.equals(TargetType.ALLY) && thrower == entity) {
	    return true;
	} else if (targetType.equals(TargetType.ALL) && entity instanceof EntityLivingBase) {
	    return true;
	}

	return false;
    }
}
