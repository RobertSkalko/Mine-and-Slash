package com.robertx22.mine_and_slash.mobs.entity;

import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class LeapingZombie extends ZombieEntity {

    public LeapingZombie(EntityType<? extends ZombieEntity> type, World world) {
        super(type, world);
    }

    public LeapingZombie(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.LEAPING_ZOMBIE, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(2, new ZombieGriefAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));

        // targetselecter is not goalselector!!
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp(MobEntity.class));
        this.targetSelector.addGoal(2, new NearestPlayerOpGoal(this));

    }

    @Override
    protected void applyEntityAI() {

    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED)
            .setBaseValue((double) 0.3);
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE)
            .setBaseValue(50);

    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
