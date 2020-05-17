package com.robertx22.mine_and_slash.entities.bosses;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.storm.LightningTotemSpell;
import com.robertx22.mine_and_slash.entities.IBossMob;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.Arrays;
import java.util.List;

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

        this.onBossTick(this);
    }

    @Override
    public void readAdditional(CompoundNBT nbt) {
        super.readAdditional(nbt);
    }

    @Override
    public void writeAdditional(CompoundNBT nbt) {
        super.writeAdditional(nbt);
    }

    BossData data = new BossData();

    @Override
    public BossData getBossData() {
        return data;
    }

    @Override
    public List<BaseSpell> getBossSpells() {
        return Arrays.asList(LightningTotemSpell.getInstance()); // todo
    }
}
