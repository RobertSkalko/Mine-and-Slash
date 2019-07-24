package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SoundUtils {
    public static void playSoundAtPlayer(PlayerEntity player, SoundEvent sound,
                                         SoundCategory category, float volume,
                                         float pitch) {
        player.world.playSound(null, player.posX, player.posY, player.posZ, sound, category, volume, pitch);
    }

    public static void playSoundAtPlayer(PlayerEntity player, SoundEvent sound,
                                         float volume, float pitch) {
        player.world.playSound(null, player.posX, player.posY, player.posZ, sound, SoundCategory.PLAYERS, volume, pitch);
    }

    public static void playSound(Entity entity, SoundEvent sound, float volume,
                                 float pitch) {

        entity.world.playSound(entity.posX, entity.posY, entity.posZ, sound, SoundCategory.PLAYERS, volume, pitch, true);

    }

    public static void playSound(World world, BlockPos pos, SoundEvent sound,
                                 float volume, float pitch) {

        world.playSound(pos.getX(), pos.getY(), pos.getZ(), sound, SoundCategory.PLAYERS, volume, pitch, true);

    }

}
