package com.robertx22.mine_and_slash.database.spells.entities.proj;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class FrostballEntity extends BaseElementalBoltEntity {
    public FrostballEntity(EntityType<? extends FrostballEntity> type, World world) {
        super(type, world);
    }

    public FrostballEntity(World worldIn) {

        super(EntityRegister.FROSTBOLT, worldIn);

    }

    public FrostballEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.FROSTBOLT, world);

    }

    @Override
    public Elements element() {
        return Elements.Water;
    }
}