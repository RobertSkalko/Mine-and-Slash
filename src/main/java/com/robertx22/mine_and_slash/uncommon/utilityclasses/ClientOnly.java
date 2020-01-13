package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.UUID;

public class ClientOnly {

    @Nullable
    public static Entity getEntityByUUID(World world, @Nullable UUID id) {

        for (Entity entity : ((ClientWorld) world).getAllEntities()) {
            if (entity.getUniqueID().equals(id)) {
                return entity;
            }
        }
        return null;

    }
}
