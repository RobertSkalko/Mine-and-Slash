package com.robertx22.mine_and_slash.spells.entities.bases.aoe;

import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.spells.entities.bases.EntityElementalBoltAOE;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class EntityFlameExplosion extends EntityElementalBoltAOE {
    public EntityFlameExplosion(EntityType<? extends EntityFlameExplosion> type,
                                World world) {
        super(type, world);
    }

    public EntityFlameExplosion(World worldIn) {

        super(EntityRegister.FIREEXPLOSION, worldIn);

    }

    public EntityFlameExplosion(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.FIREEXPLOSION, world);
    }

    @Override
    public Elements element() {
        return Elements.Fire;
    }

}