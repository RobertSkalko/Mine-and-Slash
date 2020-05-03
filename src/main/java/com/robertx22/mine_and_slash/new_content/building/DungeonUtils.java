package com.robertx22.mine_and_slash.new_content.building;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;

import java.io.File;

public class DungeonUtils {

    public static int DUNGEON_LENGTH = 30;

    public static ChunkPos getStartChunk(ChunkPos pos) {
        int chunkX = pos.x;
        int chunkZ = pos.z;
        int distToEntranceX = 11 - (chunkX % DUNGEON_LENGTH);
        int distToEntranceZ = 11 - (chunkZ % DUNGEON_LENGTH);
        chunkX += distToEntranceX;
        chunkZ += distToEntranceZ;

        return new ChunkPos(chunkX, chunkZ);
    }

    public static ChunkPos getPosFromFileName(ResourceLocation loc) {

        String name = loc.toString();

        String filename = new File(name).getName()
            .replaceAll(".nbt", "");

        String[] xz = filename.split("-");

        return new ChunkPos(Integer.parseInt(xz[0]), Integer.parseInt(xz[1]));
    }

    public static BlockPos getDungeonStartTeleportPos(ChunkPos pos) {
        BlockPos p = DungeonUtils.getStartChunk(pos)
            .asBlockPos();
        p = new BlockPos(p.getX() + 8, 55, p.getZ() + 8);
        return p;

    }

}
