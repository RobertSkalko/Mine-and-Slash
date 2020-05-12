package com.robertx22.mine_and_slash.entities.boss_summons;

import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class FastBabyZombie extends ZombieEntity {

    public FastBabyZombie(EntityType<? extends ZombieEntity> p_i48549_1_, World p_i48549_2_) {
        super(p_i48549_1_, p_i48549_2_);
    }

    public FastBabyZombie(World world) {
        super(EntityRegister.FAST_BABY_ZOMBIE, world);
    }

    public FastBabyZombie(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.FAST_BABY_ZOMBIE, world);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED)
            .setBaseValue(0.4F);

    }

    @Override
    public boolean isChild() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void readAdditional(CompoundNBT nbt) {
        super.readAdditional(nbt);
    }

    @Override
    public void writeAdditional(CompoundNBT nbt) {
        super.writeAdditional(nbt);
    }

}
