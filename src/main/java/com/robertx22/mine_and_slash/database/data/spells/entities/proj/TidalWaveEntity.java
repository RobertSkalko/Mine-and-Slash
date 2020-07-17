package com.robertx22.mine_and_slash.database.data.spells.entities.proj;

import com.robertx22.mine_and_slash.database.data.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class TidalWaveEntity extends BaseElementalBoltEntity {

    public TidalWaveEntity(EntityType<? extends TidalWaveEntity> type, World world) {
        super(type, world);
    }

    public TidalWaveEntity(World worldIn) {

        super(EntityRegister.TIDAL_WAVE, worldIn);

    }

    @Override
    public void initSpellEntity() {
        this.setNoGravity(false);
        this.setDeathTime(40);
    }

    public TidalWaveEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.TIDAL_WAVE, world);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.AIR);
    }

    @Override
    public Elements element() {
        return Elements.Water;
    }

    @Override
    public void onHit(LivingEntity entity) {
        dealSpellDamageTo(entity);

        SoundUtils.playSound(this, SoundEvents.ENTITY_GENERIC_HURT, 0.8F, 1F);
    }

    @Override
    public void onTick() {
        if (world.isRemote) {
            if (this.ticksExisted > 2) {
                for (int i = 0; i < 10; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), 0.1F);
                    ParticleUtils.spawn(ParticleRegister.BUBBLE, world, p);
                }
                if (ticksExisted % 5 == 0) {
                    ParticleUtils.spawn(ParticleTypes.DRIPPING_WATER, world, getPositionVec());
                }
            }
        }

    }

}