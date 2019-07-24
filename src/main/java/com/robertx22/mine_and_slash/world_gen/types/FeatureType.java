package com.robertx22.mine_and_slash.world_gen.types;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.template.Template;

public class FeatureType implements IWeighted {

    public FeatureType(String guid) {
        this.structureResourceLocation = new ResourceLocation(Ref.MODID, guid);
    }

    public ResourceLocation structureResourceLocation;

    public FeatureType needNonAirBlocks() {
        this.cantBeMoreThanXPercentNonAirBlocks = 90;
        this.requiresAirBlocks = true;
        return this;
    }

    boolean canSpawnInWater = false;

    public int weight = 1000;
    public int lowerByXBlocks = 0;
    public int cantBeMoreThanXPercentNonAirBlocks = 100;
    public boolean requiresAirBlocks = false;

    public boolean canSpawn(IWorld world, BlockPos pos, Template template) {

        if (requiresAirBlocks) {

        }

        if (canSpawnInWater == false) {
            if (world.getBlockState(pos)
                    .equals(Blocks.WATER) || world.getBlockState(pos.up())
                    .equals(Blocks.WATER) || world.getBlockState(pos.down())
                    .equals(Blocks.WATER)) {

                return false;
            }
        }

        return true;
    }

    /*
    int getNonAirBlockPercent(World world, BlockPos pos, Template template) {

        template.get

    }
    */

    public FeatureType lowerBy(int blocks) {
        this.lowerByXBlocks = blocks;
        return this;
    }

    public BlockPos modifyPos(BlockPos pos) {

        pos = pos.down(lowerByXBlocks);

        return pos;
    }

    @Override
    public int Weight() {
        return this.weight;
    }
}
