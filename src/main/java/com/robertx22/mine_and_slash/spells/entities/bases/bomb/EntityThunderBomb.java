package com.robertx22.mine_and_slash.spells.entities.bases.bomb;

import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.spells.entities.bases.EntityBombProjectile;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class EntityThunderBomb extends EntityBombProjectile {
    public EntityThunderBomb(EntityType<? extends EntityThunderBomb> type, World world) {
        super(type, world);
    }

    public EntityThunderBomb(World worldIn) {
        super(EntityRegister.THUNDERBOMB, worldIn);

    }

    public EntityThunderBomb(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.THUNDERBOMB, world);

    }

    @Override
    public Elements element() {
        return Elements.Thunder;
    }
}

