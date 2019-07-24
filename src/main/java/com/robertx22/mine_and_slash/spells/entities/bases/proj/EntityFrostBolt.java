package com.robertx22.mine_and_slash.spells.entities.bases.proj;

import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.spells.entities.bases.EntityElementalBolt;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class EntityFrostBolt extends EntityElementalBolt {
    public EntityFrostBolt(EntityType<? extends EntityFrostBolt> type, World world) {
        super(type, world);
    }

    public EntityFrostBolt(World worldIn) {

        super(EntityRegister.FROSTBOLT, worldIn);

    }

    public EntityFrostBolt(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.FROSTBOLT, world);

    }

    @Override
    public Elements element() {
        return Elements.Water;
    }
}