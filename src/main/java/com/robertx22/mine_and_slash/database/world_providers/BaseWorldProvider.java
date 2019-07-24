package com.robertx22.mine_and_slash.database.world_providers;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.Templates;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.world_gen.types.FeatureType;
import net.minecraft.block.BlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ModDimension;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public abstract class BaseWorldProvider extends Dimension implements IWP, IRarity, ISlashRegistryEntry<BaseWorldProvider> {

    public ModDimension moddim;
    private DimensionType type;

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.WORLD_PROVIDER;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Uncommon;
    }

    @Override
    public int Tier() {
        return 0;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Items.get(getRarityRank());
    }

    @Override
    public List<FeatureType> smallSurfaceDecorations() {
        return Arrays.asList(Templates.bigWoodPillar, Templates.smallWoodPillar, Templates.lampPillar);
    }

    @Override
    public List<FeatureType> smallTreasures() {
        return Arrays.asList(Templates.smallTreasure0, Templates.smallTreasure1, Templates.smallTreasure2);
    }

    public ResourceLocation res(String str) {

        return new ResourceLocation(Ref.MODID, str);

    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".world_type." + formattedGUID();
    }

    @Override
    public void setModDimension(ModDimension mod) {
        this.moddim = mod;
    }

    public abstract BiFunction<World, DimensionType, ? extends Dimension> classFactory();

    @Override
    public float getBonusLootMulti() {
        return 1;
    }

    @Override
    public ModDimension newModDimension() {
        return new ModDimension() {
            @Override
            public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
                return classFactory();
            }
        };
    }

    @Override
    public boolean hasSkyLight() {
        return true;
    }

    @Nullable
    public BlockPos findSpawn(ChunkPos p_206920_1_, boolean checkValid) {
        for (int i = p_206920_1_.getXStart(); i <= p_206920_1_.getXEnd(); ++i) {
            for (int j = p_206920_1_.getZStart(); j <= p_206920_1_.getZEnd(); ++j) {
                BlockPos blockpos = this.findSpawn(i, j, checkValid);
                if (blockpos != null) {
                    return blockpos;
                }
            }
        }

        return null;
    }

    @Override
    @Nullable
    public BlockPos findSpawn(int posX, int posZ, boolean checkValid) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(posX, 0, posZ);
        Biome biome = this.world.getBiome(blockpos$mutableblockpos);
        BlockState blockstate = biome.getSurfaceBuilderConfig().getTop();
        if (checkValid && !blockstate.getBlock().isIn(BlockTags.VALID_SPAWN)) {
            return null;
        } else {
            Chunk chunk = this.world.getChunk(posX >> 4, posZ >> 4);
            int i = chunk.getTopBlockY(Heightmap.Type.MOTION_BLOCKING, posX & 15, posZ & 15);
            if (i < 0) {
                return null;
            } else if (chunk.getTopBlockY(Heightmap.Type.WORLD_SURFACE, posX & 15, posZ & 15) > chunk
                    .getTopBlockY(Heightmap.Type.OCEAN_FLOOR, posX & 15, posZ & 15)) {
                return null;
            } else {
                for (int j = i + 1; j >= 0; --j) {
                    blockpos$mutableblockpos.setPos(posX, j, posZ);
                    BlockState blockstate1 = this.world.getBlockState(blockpos$mutableblockpos);
                    if (!blockstate1.getFluidState().isEmpty()) {
                        break;
                    }

                    if (blockstate1.equals(blockstate)) {
                        return blockpos$mutableblockpos.up().toImmutable();
                    }
                }

                return null;
            }
        }
    }

    public static final String RESETTABLE = "resettable";

    @Override
    public ResourceLocation getResourceLoc() {
        return new ResourceLocation(Ref.MODID, RESETTABLE + "_" + this.GUID());
    }

    public BaseWorldProvider(World world, DimensionType type) {
        super(world, type);
        this.type = type;
        this.setModDim();

    }

    @Override
    public ModDimension getModDim() {
        return moddim;
    }

    public void setModDim() {
        this.moddim = this.newModDimension();
    }

    @Nonnull
    @Override
    public ChunkGenerator<?> createChunkGenerator() {

        BiomeProviderType<SingleBiomeProviderSettings, SingleBiomeProvider> biomeType = BiomeProviderType.FIXED;
        ChunkGeneratorType chunkType = ChunkGeneratorType.SURFACE;

        OverworldGenSettings settings = (OverworldGenSettings) chunkType.createSettings();

        SingleBiomeProvider biomeProvider = biomeType.create(biomeType.createSettings()
                .setBiome(this.getBiome()));

        ChunkGenerator gen = chunkType.create(this.world, biomeProvider, settings);

        if (ModConfig.INSTANCE.Server.RESET_MAP_DIMENSIONS_ON_LOAD.get()) {
            gen.seed = MMORPG.MAP_WORLD_SEED;
        }

        return gen;
    }

    @Override
    public int Weight() {
        return getRarity().Weight();
    }

    /**
     * Do not override this.
     * <p>
     * Returns true on clients (to allow rendering of sky etc, maybe even clouds).
     * Returns false on servers (to disable Nether Portal mob spawning and sleeping
     * in beds).
     */
    @Override
    public boolean isSurfaceWorld() {
        return (this.world != null) && this.world.isRemote;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {

        double d0 = MathHelper.frac((double) worldTime / 24000.0D - 0.25D);
        double d1 = 0.5D - Math.cos(d0 * Math.PI) / 2.0D;
        return (float) (d0 * 2.0D + d1) / 3.0F;
    }

    @Override
    public long getWorldTime() {
        return 18000; // midnight, for mob spawning
    }

    /**
     * Return Vec3D with biome specific fog color
     */
    @Override
    @OnlyIn(Dist.CLIENT)
    public Vec3d getFogColor(float p_76562_1_, float p_76562_2_) {

        float f = MathHelper.cos(p_76562_1_ * ((float) Math.PI * 2F)) * 2.0F + 0.5F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        float f1 = 0.7529412F;
        float f2 = 0.84705883F;
        float f3 = 1.0F;
        f1 = f1 * (f * 0.94F + 0.06F);
        f2 = f2 * (f * 0.94F + 0.06F);
        f3 = f3 * (f * 0.91F + 0.09F);
        return new Vec3d((double) f1, (double) f2, (double) f3);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean doesXZShowFog(int x, int z) {
        return false;
    }

    @Override
    public DimensionType getType() {
        return type;
    }

}