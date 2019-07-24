package com.robertx22.mine_and_slash.spells.entities.bases.bomb;

import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.spells.entities.bases.EntityBombProjectile;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class EntityAcidBomb extends EntityBombProjectile {
    public EntityAcidBomb(EntityType<? extends EntityAcidBomb> type, World world) {
        super(type, world);
    }

    public EntityAcidBomb(World worldIn) {
        super(EntityRegister.ACIDBOMB, worldIn);

    }

    public EntityAcidBomb(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.ACIDBOMB, world);
    }

    @Override
    public Elements element() {
        return Elements.Nature;
    }

}