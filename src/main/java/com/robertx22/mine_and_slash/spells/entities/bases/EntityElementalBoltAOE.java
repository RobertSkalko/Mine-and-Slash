package com.robertx22.mine_and_slash.spells.entities.bases;

import com.robertx22.mine_and_slash.uncommon.utilityclasses.ElementalParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Utilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public abstract class EntityElementalBoltAOE extends EntityElementalBolt {

    public EntityElementalBoltAOE(EntityType<? extends Entity> type, World worldIn) {
        super(type, worldIn);

    }

    @Override
    public double radius() {
        return 3D;
    }

    @Override
    protected void onImpact(RayTraceResult result) {

        if (world.isRemote) {
            SoundUtils.playSound(this, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.4F, 0.5F);

        } else {
            SoundUtils.playSound(this, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.4F, 0.5F);

            ElementalParticleUtils.SpawnAoeParticle(element(), this, radius(), 500);

        }

        if (effect != null && data != null) {

            List<LivingEntity> entities = Utilities.getEntitiesWithinRadius(radius(), this, LivingEntity.class);

            if (entities != null) {
                for (LivingEntity entity : entities) {
                    effect.Activate(data, entity);
                }
            }
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            this.remove();
            return;
        }

    }
}
