package com.robertx22.mine_and_slash.database.spells.entities.bases;

import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Utilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public abstract class EntityElementalBoltAOE extends BaseElementalBoltEntity {

    public EntityElementalBoltAOE(EntityType<? extends Entity> type, World worldIn) {
        super(type, worldIn);
        this.shootSpeed = 1.3F;

    }

    @Override
    public double radius() {
        return 3D;
    }

    @Override
    protected void onImpact(RayTraceResult result) {

        if (world.isRemote) {

            if (result instanceof EntityRayTraceResult) {
                EntityRayTraceResult eray = (EntityRayTraceResult) result;
                if (eray.getEntity() instanceof PlayerEntity == false) {
                    SoundUtils.playSound(this, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.4F, 0.5F);
                }
            } else {
                SoundUtils.playSound(this, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.4F, 0.5F);
            }

        } else {
            SoundUtils.playSound(this, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.4F, 0.5F);

            ParticleEnum.sendToClients(this, new ParticlePacketData(getPosition(), ParticleEnum.CIRCLE_REDSTONE).radius(
                    radius()).color(element().getRGBColor()));
        }

        List<LivingEntity> entities = Utilities.getEntitiesWithinRadius(radius(), this, LivingEntity.class);

        if (entities != null) {
            for (LivingEntity entity : entities) {
                dealSpellDamageTo(entity, true);
            }
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            this.remove();
            return;
        }

    }
}
