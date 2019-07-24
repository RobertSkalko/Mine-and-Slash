package com.robertx22.mine_and_slash.spells.entities.bases.aoe;

import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.spells.entities.bases.EntityElementalBoltAOE;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class EntityFrostExplosion extends EntityElementalBoltAOE {
    public EntityFrostExplosion(EntityType<? extends EntityFrostExplosion> type,
                                World world) {
        super(type, world);
    }

    public EntityFrostExplosion(World worldIn) {

        super(EntityRegister.FROSTEXPLOSION, worldIn);

    }

    public EntityFrostExplosion(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.FROSTEXPLOSION, world);
    }

    @Override
    public Elements element() {
        return Elements.Water;
    }

}