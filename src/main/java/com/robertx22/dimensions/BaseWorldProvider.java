package com.robertx22.dimensions;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.DimensionManager;

public abstract class BaseWorldProvider extends WorldProviderSurface implements IWP {

    public BaseWorldProvider(boolean nothing) {

    }

    @Override
    public int Weight() {
	return this.UncommonWeight;

    }

    @Override
    public DimensionType getDimensionType() {
	DimensionType type = null;

	try {
	    type = DimensionManager.getProviderType(this.getDimension());
	} catch (IllegalArgumentException e) {
	}

	return type != null ? type : super.getDimensionType();
    }

    @Override
    public boolean canDropChunk(int x, int z) {
	return true;
    }

    /**
     * 
     * Do not override this.
     * 
     * Returns true on clients (to allow rendering of sky etc, maybe even clouds).
     * Returns false on servers (to disable Nether Portal mob spawning and sleeping
     * in beds).
     */
    @Override
    public boolean isSurfaceWorld() {
	return (this.world == null) ? false : this.world.isRemote;
    }

    @Override
    public boolean canRespawnHere() {
	return false;
    }

    @Override
    public String getSaveFolder() {
	return "MineAndSlash_MapWorld" + this.getDimension();
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
	return new ChunkGeneratorOverworld(world, world.rand.nextLong(), true, "");

    }
}