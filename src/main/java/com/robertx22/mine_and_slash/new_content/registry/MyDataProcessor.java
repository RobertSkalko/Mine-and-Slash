package com.robertx22.mine_and_slash.new_content.registry;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;

public class MyDataProcessor extends StructureProcessor {

    IStructureProcessorType TYPE = IStructureProcessorType.register("my_data", MyDataProcessor::new);

    public MyDataProcessor(Dynamic<?> dynamic) {

    }

    @Override
    public Template.BlockInfo process(IWorldReader p_215194_1_, BlockPos p_215194_2_, Template.BlockInfo p_215194_3_, Template.BlockInfo p_215194_4_, PlacementSettings p_215194_5_) {
        return null; //return this.blocks.contains(p_215194_4_.state.getBlock()) ? null : p_215194_4_;
    }

    @Override
    protected IStructureProcessorType getType() {
        return TYPE;
    }

    @Override
    protected <T> Dynamic<T> serialize0(DynamicOps<T> ops) {
        return null;
    }
}
