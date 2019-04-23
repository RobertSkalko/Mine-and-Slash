package com.robertx22.uncommon.utilityclasses;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;

public class SoundUtils {
    public static void playSoundAtPlayer(EntityPlayer player, SoundEvent sound, SoundCategory category, float volume,
	    float pitch) {
	player.world.playSound(null, player.posX, player.posY, player.posZ, sound, category, volume, pitch);
    }

    public static void playSoundAtPlayer(EntityPlayer player, SoundEvent sound, float volume, float pitch) {
	player.world.playSound(null, player.posX, player.posY, player.posZ, sound, SoundCategory.PLAYERS, volume,
		pitch);
    }

    public static void playSound(Entity entity, SoundEvent sound, float volume, float pitch) {

	entity.world.playSound(entity.posX, entity.posY, entity.posZ, sound, SoundCategory.PLAYERS, volume, pitch,
		true);

    }
}
