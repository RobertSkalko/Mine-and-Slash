package com.robertx22.spells.bases.projectile;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.ColoredRedstone;
import com.robertx22.effectdatas.SpellBuffEffect;
import com.robertx22.spells.bases.BaseSpellEffect;
import com.robertx22.spells.bases.DamageData;
import com.robertx22.spells.bases.projectile.Targeting.TargetType;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.SoundUtils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class EntityElementalBolt extends EntityBaseProjectile {

    protected BaseSpellEffect effect;
    protected DamageData data;

    public abstract Elements element();

    public EntityElementalBolt(World worldIn) {
	super(worldIn);

    }

    protected TargetType getTargetType() {
	return TargetType.ENEMY;
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

    public void ifDamageKilledEnemy(EntityLivingBase enemy) {
	if (enemy.getHealth() <= 0) {

	}
    }

    public List<EntityLivingBase> entitiesHit = new ArrayList();

    @Override
    protected void onImpact(RayTraceResult result) {

	if (result.entityHit != null && result.entityHit instanceof EntityLivingBase && effect != null
		&& data != null) {
	    if (world.isRemote) {
		SoundUtils.playSound(this, SoundEvents.ENTITY_GENERIC_HURT, 0.4F, 0.9F);
	    }
	    EntityLivingBase living = (EntityLivingBase) result.entityHit;

	    if (!entitiesHit.contains(living)) {
		effect.Activate(data, living);

		ifDamageKilledEnemy(living);
		entitiesHit.add(living);
	    }

	} else {
	    if (world.isRemote) {
		SoundUtils.playSound(this, SoundEvents.BLOCK_CLOTH_HIT, 0.7F, 0.9F);
	    }
	}

	if (!this.world.isRemote) {
	    if (this.getBuff().equals(SpellBuffType.Ghost_Projectile) == false) { // spell buff to go through all
										  // mobs in the way and damage
										  // them all
		this.world.setEntityState(this, (byte) 3);
		this.setDead();
	    }

	}
    }

    int ticks = 0;

    @Override
    public void onUpdate() {

	super.onUpdate();

	if (world.isRemote) {

	    ticks++;
	    if (ticks > 1) {
		ticks = 0;
		ColoredRedstone.SpawnAoeRedstone(element(), this, 0.15F, 15);
	    }

	}

    }

    public void SpawnAndShoot(BaseSpellEffect effect, DamageData data, EntityLivingBase caster) {

	this.spellType = data.spellItem.GetSpell().Type();

	SpellBuffEffect spelleffect = new SpellBuffEffect(caster, this);
	spelleffect.Activate();

	this.ignoreEntity = caster;
	this.thrower = caster;
	Vec3d look = caster.getLookVec();

	SetReady(effect, data);
	setPosition(caster.posX + look.x, caster.posY + look.y + 1.3, caster.posZ + look.z);
	shoot(caster, caster.rotationPitch, caster.rotationYaw, 0.0F, 1F, 1.0F); // start velocity

	world.spawnEntity(this);
    }

}