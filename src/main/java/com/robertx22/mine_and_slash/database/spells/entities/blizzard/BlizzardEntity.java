package com.robertx22.mine_and_slash.database.spells.entities.blizzard;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseCloudEntity;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.FrostEffect;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class BlizzardEntity extends BaseCloudEntity {

    public BlizzardEntity(World world) {
        super(EntityRegister.BLIZZARD, world);
    }

    public BlizzardEntity(EntityType type, World world) {
        super(type, world);
    }

    public BlizzardEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.BLIZZARD, world);
    }

    @Override
    public void initSpellEntity() {

    }

    @Override
    public void onHit(LivingEntity entity) {

        this.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0f, 1.0f);

        this.dealSpellDamageTo(entity, false);

        PotionEffectUtils.apply(FrostEffect.INSTANCE, 8 * 20, getCaster(), entity);

    }

    @Override
    public void summonFallParticle(Vec3d p) {
        world.addParticle(ParticleTypes.ITEM_SNOWBALL, true, p.x, p.y, p.z, 0, 0, 0);
    }

    @Override
    public int ticksToHitMobs() {
        return 20;
    }

    @Override
    public float radius() {
        return 2.5F;
    }

}