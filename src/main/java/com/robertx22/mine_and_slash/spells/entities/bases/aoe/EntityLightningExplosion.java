package com.robertx22.mine_and_slash.spells.entities.bases.aoe;

import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.spells.entities.bases.EntityElementalBoltAOE;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class EntityLightningExplosion extends EntityElementalBoltAOE {

    public EntityLightningExplosion(EntityType<? extends EntityLightningExplosion> type,
                                    World world) {
        super(type, world);
    }

    public EntityLightningExplosion(World worldIn) {

        super(EntityRegister.THUNDEREXPLOSION, worldIn);

    }

    public EntityLightningExplosion(FMLPlayMessages.SpawnEntity spawnEntity,
                                    World world) {
        super(EntityRegister.THUNDEREXPLOSION, world);
    }

    @Override
    public Elements element() {
        return Elements.Thunder;
    }

}