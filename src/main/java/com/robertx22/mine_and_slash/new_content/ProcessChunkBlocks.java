package com.robertx22.mine_and_slash.new_content;

import com.robertx22.mine_and_slash.new_content.registry.DataProcessor;
import com.robertx22.mine_and_slash.new_content.registry.DataProcessors;
import com.robertx22.mine_and_slash.saveclasses.dungeon_dimension.DungeonData;
import com.robertx22.mine_and_slash.uncommon.capability.chunk.DungeonChunkCap;
import com.robertx22.mine_and_slash.uncommon.capability.world.WorldMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.StructureBlockTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.Chunk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessChunkBlocks {

    public static void process(LivingEntity player) {

        try {
            if (WorldUtils.isMapWorldClass(player.world)) {
                ChunkPos start = new ChunkPos(player.getPosition());

                List<ChunkPos> chunks = new ArrayList<>();
                chunks.add(start);
                chunks.add(new ChunkPos(start.x + 1, start.z));
                chunks.add(new ChunkPos(start.x - 1, start.z));
                chunks.add(new ChunkPos(start.x, start.z + 1));
                chunks.add(new ChunkPos(start.x, start.z - 1));

                WorldMapCap.IWorldMapData mapdata = Load.world(player.world);

                mapdata.init(Load.playerMapData((PlayerEntity) player)
                    .getMap(), start);

                if (!mapdata.getData()
                    .hasData(start)) {
                    return;
                }

                for (ChunkPos cpos : chunks) {

                    if (!player.world.chunkExists(cpos.x, cpos.z)) {
                        continue;
                    }

                    Chunk chunk = player.world.getChunk(cpos.x, cpos.z);

                    chunk.getCapability(DungeonChunkCap.Data)
                        .ifPresent(c -> {

                            if (!c.isDoneProcessing()) {

                                DungeonData dungeonData = mapdata.getData()
                                    .getData(cpos);

                                boolean anyStructureBlocks = false;

                                if (dungeonData != null) {

                                    for (Map.Entry<BlockPos, TileEntity> entry : new HashMap<>(chunk.getTileEntityMap())
                                        .entrySet()) {
                                        TileEntity tile = entry.getValue();
                                        if (tile instanceof StructureBlockTileEntity) {
                                            BlockPos tilePos = entry.getKey();

                                            StructureBlockTileEntity struc = (StructureBlockTileEntity) tile;

                                            CompoundNBT nbt = new CompoundNBT();
                                            struc.write(nbt);
                                            String metadata = nbt.getString("metadata");
                                            // cus getmetadata is clientonly wtf

                                            boolean any = false;

                                            for (DataProcessor processor : DataProcessors.getAll()) {
                                                boolean did = processor.process(metadata, tilePos, player.world);
                                                if (did) {
                                                    any = true;
                                                }
                                            }

                                            if (any) {

                                                anyStructureBlocks = true;

                                                // only set to air if the processor didnt turn it into another block
                                                if (player.world.getBlockState(tilePos)
                                                    .getBlock() == Blocks.STRUCTURE_BLOCK) {
                                                    player.world.setBlockState(tilePos, Blocks.AIR.getDefaultState(), 2); // delete data block
                                                }

                                            } else {
                                                System.out.println("Data block with tag: " + metadata + " had no matching processors!");
                                            }
                                        }
                                    }

                                    if (anyStructureBlocks) {
                                        c.setDone();
                                        chunk.markDirty();
                                    }

                                }
                            }

                        });
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
