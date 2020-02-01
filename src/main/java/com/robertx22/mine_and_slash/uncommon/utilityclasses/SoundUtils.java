package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import net.minecraft.entity.Entity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;

public class SoundUtils {

    public static void playSound(Entity entity, SoundEvent sound, float volume, float pitch) {

        //this should be universal
        entity.world.playSound(null, entity.getPosition(), sound, SoundCategory.PLAYERS, volume, pitch);

    }

}
