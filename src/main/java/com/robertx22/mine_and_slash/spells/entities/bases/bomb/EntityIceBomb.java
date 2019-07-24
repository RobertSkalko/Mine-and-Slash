package com.robertx22.mine_and_slash.spells.entities.bases.bomb;

import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.spells.entities.bases.EntityBombProjectile;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class EntityIceBomb extends EntityBombProjectile {
    public EntityIceBomb(EntityType<? extends EntityIceBomb> type, World world) {
        super(type, world);
    }

    public EntityIceBomb(World worldIn) {
        super(EntityRegister.FROSTBOMB, worldIn);

    }

    public EntityIceBomb(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.FROSTBOMB, world);

    }

    @Override
    public Elements element() {
        return Elements.Water;
    }
}