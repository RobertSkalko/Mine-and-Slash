package com.robertx22.uncommon.utilityclasses;

import com.robertx22.mmorpg.ModConfig;

import net.minecraft.entity.Entity;
import net.minecraft.entity.INpc;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IAnimals;

public class EntityTypeUtils {

    public static float getLootMulti(Entity en) {

	if (isMob(en)) {
	    return ModConfig.EntityTypeConfig.MOB_CONFIG.LOOT_MULTI;
	} else if (isNPC(en)) {
	    return ModConfig.EntityTypeConfig.NPC_CONFIG.LOOT_MULTI;
	} else if (isAnimal(en)) {
	    return ModConfig.EntityTypeConfig.ANIMAL_CONFIG.LOOT_MULTI;
	} else {
	    return 0;
	}

    }

    public static float getExpMulti(Entity en) {

	if (isMob(en)) {

	    return ModConfig.EntityTypeConfig.MOB_CONFIG.EXP_MULTI;
	} else if (isNPC(en)) {

	    return ModConfig.EntityTypeConfig.NPC_CONFIG.EXP_MULTI;
	} else if (isAnimal(en)) {

	    return ModConfig.EntityTypeConfig.ANIMAL_CONFIG.EXP_MULTI;
	} else {
	    return 0;
	}

    }

    public static boolean isMob(Entity en) {

	return en instanceof IMob;

    }

    public static boolean isAnimal(Entity en) {

	return en instanceof IAnimals;

    }

    public static boolean isNPC(Entity en) {

	return en instanceof INpc;

    }
}
