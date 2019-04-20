package com.robertx22.spells.bases.projectile;

import com.robertx22.customitems.gearitems.weapons.ItemStaff;
import com.robertx22.spells.bases.BaseSpellEffect;
import com.robertx22.spells.bases.DamageData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityStaffProjectile extends EntityBaseProjectile {

    ItemStack staff;

    public EntityStaffProjectile(World worldIn) {
	super(worldIn);

    }

    public void SetReady(ItemStack staff) {
	this.staff = staff;
    }

    @Override
    public double radius() {
	return 0.5D;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
	if (result.entityHit != null && result.entityHit instanceof EntityLivingBase && staff != null) {

	    if (!world.isRemote) {
		try {
		    EntityLivingBase target = (EntityLivingBase) result.entityHit;

		    ItemStaff staffclass = (ItemStaff) staff.getItem();

		    UnitData sourcedata = Load.Unit(this.thrower);
		    UnitData targetdata = Load.Unit(target);

		    staffclass.mechanic().Attack(this.getThrower(), target, sourcedata, targetdata);
		} catch (Exception e) {
		    e.printStackTrace();

		}
	    }
	}

	if (!this.world.isRemote) {
	    this.world.setEntityState(this, (byte) 3);
	    this.setDead();
	}

    }

    @Override
    public void onUpdate() {

	super.onUpdate();

	if (world.isRemote) {
	    for (int i = 0; i < 5; i++) {

		this.world.spawnParticle(EnumParticleTypes.CRIT, this.posX + rand.nextFloat() * 0.2 - 0.1,
			this.posY + this.height / 2 + rand.nextFloat() * 0.2 - 0.1,
			this.posZ + rand.nextFloat() * 0.2 - 0.1, 0, 0, 0);

	    }
	}

	if (this.ticksExisted > 20) {
	    this.setDead();
	}
    }

    public void SpawnAndShoot(BaseSpellEffect effect, DamageData data, EntityLivingBase caster) {

	this.ignoreEntity = caster;
	Vec3d look = caster.getLookVec();
	this.thrower = caster;

	SetReady(caster.getHeldItemMainhand());
	setPosition(caster.posX + look.x, caster.posY + look.y + 1.3, caster.posZ + look.z);
	shoot(caster, caster.rotationPitch, caster.rotationYaw, 0.0F, 1.5F, 1.0F);

	world.spawnEntity(this);
    }

}