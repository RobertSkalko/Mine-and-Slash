package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;

public class MapManager {

    public static ResourceLocation getResourceLocation(DimensionType type) {
        ResourceLocation loc = DimensionType.getKey(type);
        return loc;
    }
}
