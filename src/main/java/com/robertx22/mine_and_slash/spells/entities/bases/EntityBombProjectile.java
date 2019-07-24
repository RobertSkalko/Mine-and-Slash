package com.robertx22.mine_and_slash.spells.entities.bases;

import com.robertx22.mine_and_slash.potion_effects.all.EnergyRegenPotion;
import com.robertx22.mine_and_slash.potion_effects.all.ManaRegenPotion;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ElementalParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Utilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public abstract class EntityBombProjectile extends EntityElementalBolt {

    @Override
    public double radius() {
        return 4D;
    }

    public EntityBombProjectile(EntityType<? extends Entity> type, World worldIn) {
        super(type, worldIn);

        this.setDeathTime(30);
        this.setAirProcTime(30);
        this.setDoExpireProc(true);

    }

    @Override
    public void tick() {

        super.tick();

        if (this.inGround) {
            //this.setPosition(getPosition().getX(), getPosition().getY() + 1.5D, getPosition()
            //       .getZ());
            this.setMotion(0, 0, 0);
            //this.setNoGravity(true);
        }

    }

    @Override
    protected boolean onExpireProc(LivingEntity caster) {
        return doEffect(caster);
    }

    public boolean doEffect(LivingEntity caster) {

        if (this.ticksExisted < this.getAirProcTime()) {
            return false;
        }

        if (world.isRemote) {

            SoundUtils.playSound(this, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.6F, 0.6F);

        } else {

            ElementalParticleUtils.SpawnAoeParticle(element(), this, this.radius(), 300);

        }

        if (world.isRemote) {
            this.world.addParticle(ParticleTypes.EXPLOSION, true, this.posX, this.posY, this.posZ, 1, 1, 1);
        }

        if (!this.world.isRemote && caster != null && effect != null) {

            List<LivingEntity> list = Utilities.getEntitiesWithinRadius(radius(), this, LivingEntity.class);

            for (int i = 0; i < list.size(); ++i) {
                LivingEntity entity = list.get(i);

                if (Load.hasUnit(entity)) {
                    effect.Activate(data, entity);

                    checkOnKill(entity);

                }

            }

        }

        return true;

    }

    public void checkOnKill(LivingEntity entity) {

        if (entity.isAlive() == false && this.getThrower() != null) {

            if (this.getBuff().equals(SpellBuffType.Energy_Regen)) {
                this.getThrower()
                        .addPotionEffect(new EffectInstance(EnergyRegenPotion.INSTANCE, 400, 2));
            } else if (this.getBuff().equals(SpellBuffType.Mana_Regen)) {
                this.getThrower()
                        .addPotionEffect(new EffectInstance(ManaRegenPotion.INSTANCE, 400, 2));
            }
        }
    }

    @Override
    protected void onImpact(RayTraceResult result) {

        switch (result.getType()) {
            case BLOCK:
                this.setMotion(0, 0, 0);
                break;
            case ENTITY:
                break;
            case MISS:
                break;
        }

    }

}