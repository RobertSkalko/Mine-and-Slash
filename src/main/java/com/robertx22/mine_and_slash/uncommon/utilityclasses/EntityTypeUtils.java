package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import net.minecraft.entity.Entity;
import net.minecraft.entity.INPC;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;

public class EntityTypeUtils {

    public enum EntityType {
        MOB("mob"),
        PLAYER("player"),
        ANIMAL("animal"),
        NPC("npc"),
        OTHER("other");

        EntityType(String id) {
            this.id = id;
        }

        public String id;

    }

    public static EntityType getType(LivingEntity entity) {

        if (isMob(entity)) {
            return EntityType.MOB;
        } else if (isAnimal(entity)) {
            return EntityType.ANIMAL;
        } else if (isNPC(entity)) {
            return EntityType.NPC;
        } else if (entity instanceof PlayerEntity) {
            return EntityType.PLAYER;
        } else {
            return EntityType.OTHER;
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
