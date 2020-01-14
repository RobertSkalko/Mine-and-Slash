package com.robertx22.mine_and_slash.database.spells.entities.bases;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpellEffect;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.DamageData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellBuffEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ElementalParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

public abstract class EntityElementalBolt extends EntityBaseProjectile {

    @OnlyIn(Dist.CLIENT)
    @Override
    public ItemStack getItem() {
        return new ItemStack(this.element().projectileItem);
    }

    protected BaseSpellEffect effect;
    protected DamageData data;

    public abstract Elements element();

    public EntityElementalBolt(EntityType<? extends Entity> type, World worldIn) {
        super(type, worldIn);
        this.shootSpeed = 1.75F;

    }

    @Override
    public double radius() {
        return 0.5D;
    }

    protected void entityInit() {
    }

    public void SetReady(BaseSpellEffect effect, DamageData data) {
        this.effect = effect;
        this.data = data;

    }

    public void ifDamageKilledEnemy(LivingEntity enemy) {
        if (enemy.getHealth() <= 0) {

        }
    }

    public List<LivingEntity> entitiesHit = new ArrayList();

    @Override
    protected void onImpact(RayTraceResult result) {

        LivingEntity entityHit = getEntityHit(result, 0.3D);

        if (entityHit != null && effect != null && data != null) {
            if (world.isRemote) {
                SoundUtils.playSound(this, SoundEvents.ENTITY_GENERIC_HURT, 0.4F, 0.9F);
            }

            if (!entitiesHit.contains(entityHit)) {
                effect.Activate(data, entityHit);

                ifDamageKilledEnemy(entityHit);
                entitiesHit.add(entityHit);
            }

        } else {
            if (world.isRemote) {
                SoundUtils.playSound(this, SoundEvents.BLOCK_STONE_HIT, 0.7F, 0.9F);
            }
        }

        if (!this.world.isRemote) {
            if (this.getBuff()
                    .equals(SpellBuffType.Ghost_Projectile) == false) { // spell buff to go through all
                // mobs in the way and damage
                // them all
                this.world.setEntityState(this, (byte) 3);
                this.remove();
                return;
            }

        }
    }

    int ticks = 0;

    @Override
    public void tick() {

        super.tick();

        if (world.isRemote) {
            if (this.ticksExisted > 2) {

                BasicParticleType particle = ParticleTypes.ENCHANTED_HIT;

                if (element().equals(Elements.Water)) {
                    particle = ParticleTypes.BUBBLE_POP;
                } else if (element().equals(Elements.Fire)) {
                    particle = ParticleTypes.FLAME;
                } else if (element().equals(Elements.Thunder)) {
                    particle = ParticleTypes.WITCH;
                } else if (element().equals(Elements.Nature)) {
                    particle = ParticleTypes.COMPOSTER;
                }

                for (int i = 0; i < 2; i++) {
                    this.world.addParticle(particle, true, this.posX + rand.nextFloat() * 0.2 - 0.1, this.posY + this
                            .getHeight() / 2 + rand.nextFloat() * 0.2 - 0.1, this.posZ + rand
                            .nextFloat() * 0.2 - 0.1, 0, 0, 0);
                }
            }

            //ParticleTypes.BUBBLE_POP water,ParticleTypes.ITEM_SNOWBALL water,
            // ParticleTypes.FIRE fire,
            // ParticleTypes.COMPOSTER nature,
            // ParticleTypes.WITCH thunder

        }

        ticks++;
        if (ticks > 1) {
            ticks = 0;
            ElementalParticleUtils.SpawnAoeParticle(element(), this, 0.15F, 15);
        }

    }

    public void SpawnAndShoot(BaseSpellEffect effect, DamageData data,
                              LivingEntity caster) {

        this.spellType = data.spellItem.GetSpell().getSpellType();

        SpellBuffEffect spelleffect = new SpellBuffEffect(caster, this);
        spelleffect.Activate();

        this.ignoreEntity = caster;
        this.thrower = caster;

        SetReady(effect, data);
        this.setPos(caster);
        shoot(caster, caster.rotationPitch, caster.rotationYaw, 0.0F, this.shootSpeed, 0.5F); // start velocity

        WorldUtils.spawnEntity(world, this);
    }

}