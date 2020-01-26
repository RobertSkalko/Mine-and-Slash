package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import net.minecraft.entity.Entity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SoundUtils {

    public static void playSound(Entity entity, SoundEvent sound, float volume, float pitch) {

        if (!entity.world.isRemote) {
            entity.playSound(sound, volume, pitch);
        } else {
            BlockPos pos = entity.getPosition();
            entity.world.playSound(
                    pos.getX(), pos.getY(), pos.getZ(), sound, SoundCategory.NEUTRAL, volume, pitch, true);

        }
    }

    public static void playSound(World world, BlockPos pos, SoundEvent sound, float volume, float pitch) {

        world.playSound(pos.getX(), pos.getY(), pos.getZ(), sound, SoundCategory.PLAYERS, volume, pitch, true);

    }

}
