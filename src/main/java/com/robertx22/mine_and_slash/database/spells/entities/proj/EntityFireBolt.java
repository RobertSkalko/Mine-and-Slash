package com.robertx22.mine_and_slash.database.spells.entities.proj;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class EntityFireBolt extends BaseElementalBoltEntity {

    public EntityFireBolt(EntityType<? extends EntityFireBolt> type, World world) {
        super(type, world);
    }

    public EntityFireBolt(World worldIn) {

        super(EntityRegister.FIREBOLT, worldIn);

    }

    public EntityFireBolt(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.FIREBOLT, world);

    }

    @Override
    public Elements element() {
        return Elements.Fire;
    }

}