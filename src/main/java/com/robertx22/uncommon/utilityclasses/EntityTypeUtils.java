package com.robertx22.uncommon.utilityclasses;

import com.robertx22.config.ModConfig;

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
	    return ModConfig.EntityTypeConfig.OTHER_CONFIG.LOOT_MULTI;
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
	    return ModConfig.EntityTypeConfig.OTHER_CONFIG.EXP_MULTI;
	}

    }

    public static boolean isMob(Entity en) {

	return en instanceof IMob;

    }

    public static boolean isAnimal(Entity en) {

	return en instanceof IAnimals;

    }

    /**
     * has to be checked first because inpc extends ianimals
     * 
     * @param en
     * @return
     */
    public static boolean isNPC(Entity en) {

	return en instanceof INpc;

    }
}
