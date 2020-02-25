package com.robertx22.mine_and_slash.new_content;

import com.mojang.datafixers.Dynamic;
import com.robertx22.mine_and_slash.database.world_providers.IWP;
import com.robertx22.mine_and_slash.new_content.building.DungeonBuilder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;
import java.util.function.Function;

public class DungeonFeature extends Feature<NoFeatureConfig> {
    public static String FEATURE_ID = "dungeon_world_feature";

    public DungeonFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> function) {
        super(function);
    }

    public static int Y_POS = 50;

    @Override
    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> chunkGenerator, Random rand, BlockPos pos, NoFeatureConfig config) {

        try {

            if (world.getDimension() instanceof IWP) {

                ChunkPos cpos = new ChunkPos(pos);

                DungeonBuilder builder = new DungeonBuilder(world.getSeed(), cpos);
                builder.build();

                if (!builder.dungeon.hasRoomForChunk(cpos)) {
                    return false;
                }

                BuiltRoom room = builder.dungeon.getRoomForChunk(cpos);

                if (room == null) {
                    return false;
                }

                MinecraftServer server = world.getWorld()
                    .getServer();
                TemplateManager templatemanager = server.getWorld(world.getDimension()
                    .getType())
                    .getStructureTemplateManager();

                Template template = templatemanager.getTemplate(room.structure);
                PlacementSettings settings = (new PlacementSettings()).setMirror(Mirror.NONE)
                    .setRotation(Rotation.NONE)
                    .setIgnoreEntities(false)
                    .setChunk(cpos);
                settings.setBoundingBox(settings.getBoundingBox());

                // settings.addProcessor();

                settings.setRotation(room.data.rotation);
                BlockPos position = new BlockPos(cpos.getXStart(), Y_POS, cpos.getZStart());

                if (template == null) {
                    System.out.println("FATAL ERROR: Structure does not exist (" + room.structure + ")");
                    return false;
                }

                // next if the structure is to be rotated then it must also be offset, because rotating a structure also moves it
                if (room.data.rotation == Rotation.COUNTERCLOCKWISE_90) {
                    // west: rotate CCW and push +Z
                    settings.setRotation(Rotation.COUNTERCLOCKWISE_90);
                    position = position.add(0, 0, template.getSize()
                        .getZ() - 1);
                } else if (room.data.rotation == Rotation.CLOCKWISE_90) {
                    // east rotate CW and push +X
                    settings.setRotation(Rotation.CLOCKWISE_90);
                    position = position.add(template.getSize()
                        .getX() - 1, 0, 0);
                } else if (room.data.rotation == Rotation.CLOCKWISE_180) {
                    // south: rotate 180 and push both +X and +Z
                    settings.setRotation(Rotation.CLOCKWISE_180);
                    position = position.add(template.getSize()
                        .getX() - 1, 0, template.getSize()
                        .getZ() - 1);
                } else //if (nextRoom.rotation == Rotation.NONE)
                {                // north: no rotation
                    settings.setRotation(Rotation.NONE);
                }

                boolean success = template.addBlocksToWorld(world, position, settings, 2);

                return success;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}