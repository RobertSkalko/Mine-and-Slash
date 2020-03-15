package com.robertx22.mine_and_slash.new_content.data_processors;

import com.robertx22.mine_and_slash.new_content.data_processors.bases.ChunkProcessData;
import com.robertx22.mine_and_slash.new_content.registry.DataProcessor;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class RemoveAllBesidesOneProcessor extends DataProcessor {

    public RemoveAllBesidesOneProcessor() {
        super("remove_all_besides_one", Type.CONTAINS);
    }

    @Override
    public void processImplementation(String key, BlockPos pos, IWorld world, ChunkProcessData data) {

        try {
            String[] parts = key.split(":");
            String blockID = parts[1];

            Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(blockID));

            List<BlockPos> list = new ArrayList<>();

            Function<BlockPos, Boolean> function = null;

            if (blockID.equals("button")) {
                function = x -> {

                    BlockState state = world.getBlockState(x);

                    if (state.getBlock()
                        instanceof AbstractButtonBlock) {
                        return true;

                    }
                    return false;
                };
            }

            for (BlockPos blockPos : data.getBlockPosList()) {
                if (function.apply(blockPos)) {
                    list.add(blockPos);
                }
            }
            if (!list.isEmpty()) {

                list.remove(RandomUtils.RandomRange(0, list.size() - 1));

                list.forEach(x -> world.setBlockState(x, Blocks.AIR.getDefaultState(), 2));

            } else {
                System.out.println("Didn't find any correct blocks?");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

