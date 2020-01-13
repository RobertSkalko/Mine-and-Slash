package com.robertx22.mine_and_slash.database.spells.entities.blizzard;

import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class BlizzardCloudEntity extends CloudEntity {
    public BlizzardCloudEntity(World world) {
        super(EntityRegister.FROST_BLIZZARD, world);
    }

    public BlizzardCloudEntity(EntityType type, World world) {
        super(type, world);
    }

    public BlizzardCloudEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.FROST_BLIZZARD, world);
    }
}
