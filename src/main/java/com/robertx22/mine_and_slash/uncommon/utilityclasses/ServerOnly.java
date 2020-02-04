package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.UUID;

public class ServerOnly {
    @Nullable
    public static Entity getEntityByUUID(World world, @Nullable UUID id) {

        if (world instanceof ServerWorld) {
            return ((ServerWorld) world).getEntityByUuid(id);
        }

        return null;

    }
}
