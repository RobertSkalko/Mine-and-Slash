package com.robertx22.mine_and_slash.entities.bosses;

import com.robertx22.mine_and_slash.entities.IBossMob;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class ThunderZombieBoss extends ZombieEntity implements IBossMob {

    public ThunderZombieBoss(EntityType<? extends ZombieEntity> type, World world) {
        super(type, world);
    }

    public ThunderZombieBoss(World world) {
        super(EntityRegister.THUNDER_ZOMBIE_BOSS, world);
    }

    public ThunderZombieBoss(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.THUNDER_ZOMBIE_BOSS, world);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED)
            .setBaseValue(0.3F);

    }

    @Override
    public boolean isChild() {
        return false;
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
