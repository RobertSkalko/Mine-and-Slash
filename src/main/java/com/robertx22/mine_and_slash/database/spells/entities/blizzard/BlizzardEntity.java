package com.robertx22.mine_and_slash.database.spells.entities.blizzard;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseCloudEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellEffectDamage;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
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
        if (getServerSpellData().effect instanceof SpellEffectDamage) {
            SpellEffectDamage dmg = (SpellEffectDamage) getServerSpellData().effect;
            dmg.knockback = false;
        }
    }

    @Override
    public void onHit(LivingEntity entity) {

        this.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0f, 1.0f);

        this.getServerSpellData().effect.Activate(this.getServerSpellData().data, entity);

        entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 10));

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