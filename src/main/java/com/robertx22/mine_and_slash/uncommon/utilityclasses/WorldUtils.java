package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import com.robertx22.mine_and_slash.config.dimension_configs.DimensionConfig;
import com.robertx22.mine_and_slash.config.dimension_configs.DimensionsContainer;
import com.robertx22.mine_and_slash.database.world_providers.BaseWorldProvider;
import com.robertx22.mine_and_slash.database.world_providers.IWP;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.saveclasses.mapitem.MapAffixData;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorldUtils {

    public static void spawnEntity(World world, Entity entity) {

        world.addEntity(entity);

    }

    public static boolean isNearSurface(BlockPos pos, World world, int buffer) {

        BlockPos surface = WorldUtils.getSurface(world, pos);

        if (pos.getY() > surface.getY() - buffer) {
            return true;
        }

        return false;
    }

    public static List<MapAffixData> getAllAffixesThatAffect(
            PlayerMapCap.IPlayerMapData mapdata, LivingEntity entity) {

        List<MapAffixData> list = new ArrayList<>();

        if (mapdata != null) {
            list.addAll(MapItemData.getAllAffixesThatAffect(mapdata.getMap().affixes, entity));
        }

        list.addAll(MapItemData.getAllAffixesThatAffect(getAllMapAffixes(entity.world), entity));

        return list;
    }

    public static List<MapAffixData> getAllMapAffixes(World world) {

        List<MapAffixData> list = new ArrayList<>();

        if (WorldUtils.isMapWorldClass(world)) {
            IWP iwp = (IWP) world.getDimension();

            list.addAll(iwp.getMapAffixes());

        }

        return list;
    }

    public static BlockPos getPosByLevel(World world, int lvl) {

        DimensionConfig config = DimensionsContainer.INSTANCE.getConfig(world);

        BlockPos pos = LevelUtils.getAreaPosOfLevel(world, lvl, config);

        pos = getSurface(world, pos).up(2);

        if (pos.getY() > world.getHeight()) {
            pos = new BlockPos(pos.getX(), world.getHeight() - 1, pos.getZ());
        }

        return pos;

    }

    public static BlockPos getSurfaceCenterOfChunk(IWorld world, BlockPos pos) {

        int x = world.getChunk(pos).getPos().x + 8;
        int z = world.getChunk(pos).getPos().z + 8;

        pos = furtherby8(pos);

        pos = getSurface(world, pos);

        return pos;
    }

    public static BlockPos furtherby8(BlockPos pos) {

        int x = 0;
        int z = 0;

        if (pos.getX() > 0) {
            x = pos.getX() + 8;
        } else {
            x = pos.getX() - 8;
        }

        if (pos.getZ() > 0) {
            z = pos.getZ() + 8;
        } else {
            z = pos.getZ() - 8;
        }

        pos = new BlockPos(x, pos.getY(), z);

        return pos;

    }

    public static boolean surfaceIsWater(IWorld world, BlockPos pos) {

        BlockPos surface = getSurface(world, pos);

        for (BlockPos x : Arrays.asList(surface.up(), surface.up(2), surface.down(), surface
                .down(2), surface)) {
            if (world.getBlockState(x).getMaterial() == Material.WATER) {
                return true;
            }
        }

        return false;

    }

    public static BlockPos getSurface(IWorld world, BlockPos pos) {

        pos = new BlockPos(pos.getX(), world.getSeaLevel(), pos.getZ());

        boolean goingDown = world.isAirBlock(pos);

        while (world.isAirBlock(pos) || world.getBlockState(pos)
                .getBlock() instanceof LeavesBlock) {

            if (goingDown) {
                pos = pos.down();
            } else {
                pos = pos.up();
            }

        }

        while (world.isAirBlock(pos.up()) == false) {
            pos = pos.up();
        }

        return pos.up();

    }

    public static boolean isMapWorld(IWorld world) {

        if (world == null) {
            return false;
        }

        if (world.getDimension() instanceof BaseWorldProvider == false) {
            return DimensionsContainer.INSTANCE.getConfig(world).MAP_TIER > 0;
        }

        return true;

    }

    public static boolean isMapWorldClass(World world) {

        if (world == null) {
            return false;
        }

        return world.getDimension() instanceof BaseWorldProvider;
    }

    public static IWP getIWP(IWorld theworld) {

        if (theworld.getDimension() instanceof IWP) {

            return (IWP) theworld.getDimension();

        }
        return null;
    }

    public static int getTier(World world, PlayerEntity player) {

        if (DimensionsContainer.INSTANCE.hasConfig(world)) {
            return DimensionsContainer.INSTANCE.getConfig(world).MAP_TIER;
        } else {

            return Load.playerMapData(player).getTier();
        }

    }

    public static boolean dropsUniques(World world) {

        if (isMapWorld(world) == true) {
            return true;
        }

        return DimensionsContainer.INSTANCE.getConfig(world).DROPS_UNIQUE_ITEMS;
    }

}
