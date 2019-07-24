package com.robertx22.mine_and_slash.world_gen.processors;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;

import javax.annotation.Nullable;

public class ChestProcessor extends StructureProcessor {

    public ChestProcessor() {

    }

    public ChestProcessor(int chance) {
        this.treasureChance = chance;

    }

    static int treasureChance = 70;

    @Nullable
    @Override
    public Template.BlockInfo process(IWorldReader iWorldReader, BlockPos blockPos,
                                      Template.BlockInfo blockInfo,
                                      Template.BlockInfo blockInfo1,
                                      PlacementSettings placementSettings) {

        Block block = blockInfo1.state.getBlock();

        if (block.equals(Blocks.CHEST)) {

            if (RandomUtils.roll(treasureChance)) {
                return new Template.BlockInfo(blockInfo1.pos, BlockRegister.EGG_LOOT_CRATE_BLOCK
                        .getDefaultState(), blockInfo1.nbt);
            } else {
                return new Template.BlockInfo(blockInfo1.pos, Blocks.AIR.getDefaultState(), blockInfo1.nbt);
            }

        }

        return blockInfo1;

    }

    @Override
    protected IStructureProcessorType getType() {
        return IStructureProcessorType.RULE;
    }

    @Override
    protected <T> Dynamic<T> serialize0(DynamicOps<T> dynamicOps) {
        return new Dynamic(dynamicOps);
    }

}
