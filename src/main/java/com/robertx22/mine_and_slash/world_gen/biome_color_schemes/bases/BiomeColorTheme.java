package com.robertx22.mine_and_slash.world_gen.biome_color_schemes.bases;

import com.robertx22.mine_and_slash.world_gen.biome_color_schemes.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import java.util.HashMap;
import java.util.List;

public abstract class BiomeColorTheme {

    public BiomeColorTheme() {

        blocksReplaceMap.put(Blocks.OAK_LOG, new BlockReplacement(OAK_LOG()));
        blocksReplaceMap.put(Blocks.OAK_PLANKS, new BlockReplacement(OAK_PLANKS()));
        blocksReplaceMap.put(Blocks.OAK_STAIRS, new BlockReplacement(OAK_STAIRS()));
        blocksReplaceMap.put(Blocks.OAK_SLAB, new BlockReplacement(OAK_SLABS()));
        blocksReplaceMap.put(Blocks.OAK_FENCE, new BlockReplacement(OAK_FENCE()));

    }

    public abstract List<BlockWeight> OAK_LOG();

    public abstract List<BlockWeight> OAK_PLANKS();

    public abstract List<BlockWeight> OAK_STAIRS();

    public abstract List<BlockWeight> OAK_SLABS();

    public abstract List<BlockWeight> OAK_FENCE();

    public HashMap<Block, BlockReplacement> blocksReplaceMap = new HashMap<>();

    public static final BiomeColorTheme NETHER = new NetherTheme();
    public static final BiomeColorTheme NORMAL = new NormalTheme();
    public static final BiomeColorTheme WINTER_SPRUCE = new WinterSpruceTheme();
    public static final BiomeColorTheme DESERT = new DesertTheme();
    public static final BiomeColorTheme RED_DESERT = new RedDesertTheme();
    public static final BiomeColorTheme STONE = new StoneTheme();
    public static final BiomeColorTheme ACACIA = new AcaciaTheme();
    public static final BiomeColorTheme GREEN = new GreenTheme();

}
