package com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterTargetContext;
import com.robertx22.mine_and_slash.db_lists.initializers.Synergies;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class FireballEntity extends BaseElementalBoltEntity {

    public FireballEntity(EntityType<? extends FireballEntity> type, World world) {
        super(type, world);
    }

    public FireballEntity(World worldIn) {

        super(EntityRegister.FIREBOLT, worldIn);

    }

    public FireballEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.FIREBOLT, world);
    }

    @Override
    public Elements element() {
        return Elements.Fire;
    }

    @Override
    public void onHit(LivingEntity entity) {
        dealSpellDamageTo(entity);

        if (Synergies.FIREBALL_BURN_CONSUME.has(getCaster())) {
            Synergies.FIREBALL_BURN_CONSUME.tryActivate(new CasterTargetContext(getCaster(), entity));
        }

        SoundUtils.playSound(this, SoundEvents.ENTITY_GENERIC_HURT, 0.8F, 1F);
    }

    @Override
    public void onTick() {

        if (world.isRemote) {
            if (this.ticksExisted > 1) {
                for (int i = 0; i < 3; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), 0.15F);
                    ParticleUtils.spawn(ParticleTypes.FLAME, world, p);
                }
            }
        }

    }

}