package com.robertx22.mine_and_slash.database.spells.entities.blizzard;

import com.robertx22.mine_and_slash.database.spells.entities.bases.InvisibleEntity;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;

public class FrostBlizzardEntity extends InvisibleEntity {

    public FrostBlizzardEntity(World world) {
        super(null, world);
    }

    @Override
    protected void registerData() {

    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return null;
    }
}
