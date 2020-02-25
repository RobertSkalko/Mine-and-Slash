package com.robertx22.mine_and_slash.new_content.dimension;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DungeonChunkGenerator extends OverworldChunkGenerator {

    private Random randomSeed;

    public DungeonChunkGenerator(IWorld world, BiomeProvider provider, OverworldGenSettings settingsIn) {
        super(world, provider, settingsIn);

        randomSeed = world.getRandom();
    }

    @Override
    public void spawnMobs(WorldGenRegion region) {

    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EntityClassification p_177458_1_, BlockPos p_177458_2_) {
        return new ArrayList<>();
    }

    @Override
    public void spawnMobs(ServerWorld worldIn, boolean spawnHostileMobs, boolean spawnPeacefulMobs) {

    }

    @Override
    public int getGroundHeight() {
        return this.world.getSeaLevel() + 1;
    }

    @Override
    public int getSeaLevel() {
        return 20;
    }

    public void makeBase(IWorld worldIn, IChunk chunkIn) {
        int x = chunkIn.getPos().x;
        int z = chunkIn.getPos().z;
        long worldSeed = world.getSeed();
        randomSeed.setSeed((worldSeed + (long) (x * x * 4987142) + (long) (x * 5947611) + (long) (z * z) * 4392871L + (long) (z * 389711) ^ worldSeed));

        for (int px = 0; px < 16; px++) {
            for (int py = 1; py < 255; py++) {
                for (int pz = 0; pz < 16; pz++) {
                    if (py < 2) {
                        chunkIn.setBlockState(new BlockPos(px, py, pz), Blocks.BEDROCK.getDefaultState(), false);
                    } else if (py < 50) {
                        // for debugging mostly but it also kind of looks good when you're in creative mode
                        if (true) {
                            chunkIn.setBlockState(new BlockPos(px, py, pz), Blocks.ANDESITE.getDefaultState(), false);
                        } else {
                            chunkIn.setBlockState(new BlockPos(px, py, pz), Blocks.SANDSTONE.getDefaultState(), false);
                        }
                    }
                }
            }
        }

    }

    @Override
    protected void makeBedrock(IChunk chunkIn, Random rand) {

    }
}
