package com.robertx22.dimensions;

import java.io.File;
import java.io.FileFilter;

import javax.annotation.Nullable;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.gen.ChunkProviderServer;

public class WorldFileUtils {
    private static final FileFilter FILE_FILTER_NO_LEVEL = new FileFilter() {
	public boolean accept(File name) {
	    return name.getName().equals("level.dat") == false;
	}
    };

    @Nullable
    public static File getWorldDirectory(World world) {
	IChunkProvider chunkProvider = world.getChunkProvider();

	if (chunkProvider instanceof ChunkProviderServer) {
	    ChunkProviderServer chunkProviderServer = (ChunkProviderServer) chunkProvider;
	    IChunkLoader chunkLoader = chunkProviderServer.chunkLoader;

	    if (chunkLoader instanceof AnvilChunkLoader) {
		return ((AnvilChunkLoader) chunkLoader).chunkSaveLocation;
	    }

	    return null;
	}
	// If this method gets called before ChunkProviderServer has been set yet,
	// then we mimic the vanilla code in AnvilSaveHandler#getChunkLoader() to get
	// the directory.
	else {
	    return getWorldDirectoryDirectly(world, true);
	}
    }

    private static File getWorldDirectoryDirectly(World world, boolean mkDirs) {
	File mainWorldDir = world.getSaveHandler().getWorldDirectory();
	File dimensionDir = mainWorldDir;
	String dimensionDirName = world.provider.getSaveFolder();

	if (dimensionDirName != null) {
	    dimensionDir = new File(mainWorldDir, dimensionDirName);

	    if (mkDirs) {
		dimensionDir.mkdirs();
	    }
	}

	return dimensionDir;
    }
}
