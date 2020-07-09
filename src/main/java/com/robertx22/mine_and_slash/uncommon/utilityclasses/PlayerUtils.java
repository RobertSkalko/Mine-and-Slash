package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

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

    private static final ITeleporter PORTALLESS = new ITeleporter() {

        @Override
        public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw,
                                  Function<Boolean, Entity> repositionEntity) {
            return repositionEntity.apply(false);
        }
    };

    public static Entity changeDimension(ServerPlayerEntity player, DimensionType destination, BlockPos pos) {

        System.out.println("Teleporting player to " + pos.toString() + " with mine and slash. ");

        player.setMotion(0, 0, 0);

        player = (ServerPlayerEntity) player.changeDimension(destination, PORTALLESS);

        EntityUtils.setLoc(player, new Vec3d(pos), player.rotationYaw, player.rotationPitch);

        return player;

    }

    /*
            public static Entity changeDimension(ServerPlayerEntity player, DimensionType destination, BlockPos pos) {
            if (!net.minecraftforge.common.ForgeHooks.onTravelToDimension(player, destination))
                return null;
            player.invulnerableDimensionChange = true;
            DimensionType dimensiontype = player.dimension;
            if (dimensiontype == DimensionType.THE_END && destination == DimensionType.OVERWORLD) {
                player.detach();
                player.getServerWorld().removePlayer(player);
                if (!player.queuedEndExit) {
                    player.queuedEndExit = true;

                }

                return player;
            } else {
                ServerWorld serverworld = player.server.getWorld(dimensiontype);
                player.dimension = destination;
                ServerWorld serverworld1 = player.server.getWorld(destination);
                WorldInfo worldinfo = player.world.getWorldInfo();

                player.connection.sendPacket(
                        new SRespawnPacket(destination, WorldInfo.byHashing(worldinfo.getSeed()), worldinfo
                        .getGenerator(),
                                           player.interactionManager.getGameType()
                        ));
                player.connection.sendPacket(
                        new SServerDifficultyPacket(worldinfo.getDifficulty(), worldinfo.isDifficultyLocked()));

                PlayerList playerlist = player.server.getPlayerList();
                playerlist.updatePermissionLevel(player);
                serverworld.removeEntity(player,
                                         true
                ); //Forge: the player entity is moved to the new world, NOT cloned. So keep the data alive with no
                // matching invalidate call.
                player.revive();
                float f1 = player.rotationYaw;
                serverworld.getProfiler().startSection("moving");

                // MY STUFF
                player.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), f1, 0.0F);
                player.addPotionEffect(new EffectInstance(TeleportProtection.INSTANCE, 10 * 20));
                // MY STUFF

                serverworld.getProfiler().endSection();
                player.setWorld(serverworld1);
                serverworld1.func_217447_b(player);
                player.func_213846_b(serverworld);
                player.connection.setPlayerLocation(pos.getX(), pos.getY(), pos.getZ(), f1, 0.0F);
                player.interactionManager.setWorld(serverworld1);
                player.connection.sendPacket(new SPlayerAbilitiesPacket(player.abilities));
                playerlist.sendWorldInfo(player, serverworld1);
                playerlist.sendInventory(player);

                for (EffectInstance effectinstance : player.getActivePotionEffects()) {
                    player.connection.sendPacket(new SPlayEntityEffectPacket(player.getEntityId(), effectinstance));
                }

                player.connection.sendPacket(new SPlaySoundEventPacket(1032, BlockPos.ZERO, 0, false));
                player.lastExperience = -1;
                player.lastHealth = -1.0F;
                player.lastFoodLevel = -1;

                net.minecraftforge.fml.hooks.BasicEventHooks.firePlayerChangedDimensionEvent(
                        player, dimensiontype, destination);
                return player;
            }
        }
    */
    @Nullable
    public static PlayerEntity nearestPlayer(ServerWorld world, LivingEntity entity) {

        Optional<ServerPlayerEntity> player = world.getPlayers()
            .stream()
            .min(Comparator.comparingDouble(entity::getDistanceSq));

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
