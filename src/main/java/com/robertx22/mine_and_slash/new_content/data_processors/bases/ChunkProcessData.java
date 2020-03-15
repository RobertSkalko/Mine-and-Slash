package com.robertx22.mine_and_slash.new_content.data_processors.bases;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ChunkProcessData {

    public ChunkProcessData(Chunk chunk) {
        this.chunk = chunk;

    }

    public Chunk chunk;
    public boolean chanceChest = false;

    public void iterateBlocks(Function<BlockPos, BlockPos> function) {

        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 100; y++) {
                for (int z = 0; z < 16; z++) {
                    function.apply(chunk.getPos()
                        .asBlockPos()
                        .add(
                            x, y, z));
                }
            }
        }

    }

    public List<BlockPos> getBlockPosList() {

        List<BlockPos> list = new ArrayList<>();

        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 100; y++) {
                for (int z = 0; z < 16; z++) {
                    list.add(chunk.getPos()
                        .asBlockPos()
                        .add(
                            x, y, z));
                }
            }
        }

        return list;
    }

}
