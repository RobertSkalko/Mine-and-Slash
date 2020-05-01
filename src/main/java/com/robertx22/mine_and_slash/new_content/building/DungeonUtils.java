package com.robertx22.mine_and_slash.new_content.building;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;

public class DungeonUtils {
    public static ChunkPos getStartChunk(ChunkPos pos) {
        int chunkX = pos.x;
        int chunkZ = pos.z;
        int distToEntranceX = 8 - (chunkX % 30);
        int distToEntranceZ = 11 - (chunkZ % 30);
        chunkX += distToEntranceX;
        chunkZ += distToEntranceZ;

        return new ChunkPos(chunkX, chunkZ);
    }

    public static String getStringFromFileName(ResourceLocation loc, String search) {

        String name = loc.toString();

        int start = name.indexOf(search);

        int end = name.indexOf("]", start);

        return name.substring(start, end);
    }

    public static BlockPos getDungeonStartTeleportPos(ChunkPos pos) {
        BlockPos p = DungeonUtils.getStartChunk(pos)
            .asBlockPos();
        p = new BlockPos(p.getX() + 8, 55, p.getZ() + 8);
        return p;

    }

}
