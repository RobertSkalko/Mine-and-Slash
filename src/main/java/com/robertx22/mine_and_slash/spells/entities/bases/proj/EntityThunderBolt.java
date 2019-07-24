package com.robertx22.mine_and_slash.spells.entities.bases.proj;

import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.spells.entities.bases.EntityElementalBolt;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class EntityThunderBolt extends EntityElementalBolt {

    public EntityThunderBolt(EntityType<? extends EntityThunderBolt> type, World world) {
        super(type, world);
    }

    public EntityThunderBolt(World worldIn) {

        super(EntityRegister.THUNDERBOLT, worldIn);

    }

    public EntityThunderBolt(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.THUNDERBOLT, world);

    }

    @Override
    public Elements element() {
        return Elements.Thunder;
    }

}