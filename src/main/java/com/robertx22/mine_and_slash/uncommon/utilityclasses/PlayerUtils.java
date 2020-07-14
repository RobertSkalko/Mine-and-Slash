package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.Optional;

public class PlayerUtils {

    public static BlockPos getBedLocation(PlayerEntity player) {

        BlockPos pos = null;

        if (pos == null) {
            Optional<BlockPos> opt = player.getBedPosition();
            if (opt.isPresent()) {
                pos = opt.get();
            }
        }
        if (pos == null) {
            pos = player.getBedLocation(player.world.getDimension()
                .getType());
        }
        if (pos == null) {
            pos = player.getBedLocation();
        }

        return pos;
    }

    public static void giveItem(ItemStack stack, PlayerEntity player) {
        if (player.addItemStackToInventory(stack) == false) {
            player.entityDropItem(stack, 1F);
        }
        player.inventory.markDirty();
    }

    @Nullable
    public static PlayerEntity nearestPlayer(ServerWorld world, LivingEntity entity) {
        return nearestPlayer(world, entity.getPositionVec());
    }

    @Nullable
    public static PlayerEntity nearestPlayer(ServerWorld world, Vec3d pos) {

        Optional<ServerPlayerEntity> player = world.getPlayers()
            .stream()
            .min(Comparator.comparingDouble(x -> x.getDistanceSq(pos)));

        if (player.isPresent()) {
            return player.get();
        }

        return null;

    }

    public static CompoundNBT getPersistentNBT(PlayerEntity player) {

        CompoundNBT nbt = null;

        try {

            INBT basenbt = player.getPersistentData();

            if (basenbt != null) {
                nbt = (CompoundNBT) basenbt;
            }
            if (nbt == null) {
                nbt = new CompoundNBT();
            }

        } catch (Exception e) {
            nbt = new CompoundNBT();
            e.printStackTrace();
        }

        return nbt;

    }

    public static void setPestistentNBT(PlayerEntity player, CompoundNBT nbt) {

        player.getPersistentData()
            .put(PlayerEntity.PERSISTED_NBT_TAG, nbt);
    }

}
