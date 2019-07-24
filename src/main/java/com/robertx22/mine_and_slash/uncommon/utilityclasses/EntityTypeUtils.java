package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.config.whole_mod_entity_configs.ModEntityConfigs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.INPC;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.AnimalEntity;

public class EntityTypeUtils {

    public static float getLootMulti(Entity en) {

        try {
            String monster_id = en.getType().getRegistryName().toString();
            String mod_id = en.getType().getRegistryName().getNamespace();

            float val = -1;

            boolean has = false;

            if (ModEntityConfigs.INSTANCE.allMobsInAMod.containsKey(mod_id)) {
                val = (float) ModEntityConfigs.INSTANCE.allMobsInAMod.get(mod_id).LOOT_MULTI;
                has = true;
            }
            if (ModEntityConfigs.INSTANCE.specificMobs.containsKey(monster_id)) {
                val = (float) ModEntityConfigs.INSTANCE.specificMobs.get(monster_id).LOOT_MULTI;
                has = true;
            }
            if (has) {
                return val;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isMob(en)) {
            return ModConfig.INSTANCE.EntityTypeConfig.MOB_CONFIG.LOOT_MULTI.get()
                    .floatValue();
        } else if (isNPC(en)) {
            return ModConfig.INSTANCE.EntityTypeConfig.NPC_CONFIG.LOOT_MULTI.get()
                    .floatValue();
        } else if (isAnimal(en)) {
            return ModConfig.INSTANCE.EntityTypeConfig.ANIMAL_CONFIG.LOOT_MULTI.get()
                    .floatValue();
        } else {
            return ModConfig.INSTANCE.EntityTypeConfig.OTHER_CONFIG.LOOT_MULTI.get()
                    .floatValue();
        }

    }

    public static float getExpMulti(Entity en) {

        try {
            String monster_id = en.getType().getRegistryName().toString();
            String mod_id = en.getType().getRegistryName().getNamespace();

            float val = -1;

            boolean has = false;

            if (ModEntityConfigs.INSTANCE.allMobsInAMod.containsKey(mod_id)) {
                val = (float) ModEntityConfigs.INSTANCE.allMobsInAMod.get(mod_id).EXP_MULTI;
                has = true;
            }
            if (ModEntityConfigs.INSTANCE.specificMobs.containsKey(monster_id)) {
                val = (float) ModEntityConfigs.INSTANCE.specificMobs.get(monster_id).EXP_MULTI;
                has = true;
            }
            if (has) {
                return val;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isMob(en)) {
            return ModConfig.INSTANCE.EntityTypeConfig.MOB_CONFIG.EXP_MULTI.get()
                    .floatValue();
        } else if (isNPC(en)) {
            return ModConfig.INSTANCE.EntityTypeConfig.NPC_CONFIG.EXP_MULTI.get()
                    .floatValue();
        } else if (isAnimal(en)) {
            return ModConfig.INSTANCE.EntityTypeConfig.ANIMAL_CONFIG.EXP_MULTI.get()
                    .floatValue();
        } else {
            return ModConfig.INSTANCE.EntityTypeConfig.OTHER_CONFIG.EXP_MULTI.get()
                    .floatValue();
        }

    }

    public static boolean isMob(Entity en) {

        return en instanceof IMob;

    }

    public static boolean isAnimal(Entity en) {

        return en instanceof AnimalEntity;

    }

    /**
     * has to be checked first because inpc extends ianimals
     *
     * @param en
     * @return
     */
    public static boolean isNPC(Entity en) {

        return en instanceof INPC;

    }
}
