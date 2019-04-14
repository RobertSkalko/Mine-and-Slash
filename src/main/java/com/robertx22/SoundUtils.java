package com.robertx22;

import net.minecraft.entity.Entity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;

public class SoundUtils {

    public static void playSound(Entity entity, SoundEvent sound, float volume, float pitch) {

	entity.world.playSound(entity.posX, entity.posY, entity.posZ, sound, SoundCategory.PLAYERS, volume, pitch,
		true);

    }
}
