package com.robertx22.customitems.gearitems;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MyEntityArrow extends EntitySpectralArrow {

    ItemStack weapon = ItemStack.EMPTY;
    UnitData sourceData = null;

    private static final Predicate<Entity> ARROW_TARGETS = Predicates.and(EntitySelectors.NOT_SPECTATING,
	    EntitySelectors.IS_ALIVE, new Predicate<Entity>() {
		public boolean apply(@Nullable Entity p_apply_1_) {
		    return p_apply_1_.canBeCollidedWith();
		}
	    });
    private static final DataParameter<Byte> CRITICAL = EntityDataManager.<Byte>createKey(MyEntityArrow.class,
	    DataSerializers.BYTE);
    private int xTile;
    private int yTile;
    private int zTile;
    private Block inTile;
    private int inData;
    protected boolean inGround;
    protected int timeInGround;
    /** 1 if the player can pick up the arrow */
    public MyEntityArrow.PickupStatus pickupStatus;
    /** Seems to be some sort of timer for animating an arrow. */
    public int arrowShake;
    /** The owner of this arrow. */
    public Entity shootingEntity;
    private int ticksInGround;
    private int ticksInAir;
    private double damage;
    /** The amount of knockback an arrow applies when it hits a mob. */
    private int knockbackStrength;

    private MyEntityArrow(World worldIn) {
	super(worldIn);
	this.xTile = -1;
	this.yTile = -1;
	this.zTile = -1;
	this.pickupStatus = MyEntityArrow.PickupStatus.DISALLOWED;
	this.damage = 2.0D;
	this.setSize(0.5F, 0.5F);
    }

    private MyEntityArrow(World worldIn, double x, double y, double z) {
	this(worldIn);
	this.setPosition(x, y, z);
    }

    public MyEntityArrow(World worldIn, EntityLivingBase shooter, UnitData sourceData, ItemStack weapon) {

	this(worldIn, shooter.posX, shooter.posY + (double) shooter.getEyeHeight() - 0.10000000149011612D,
		shooter.posZ);
	this.shootingEntity = shooter;

	this.weapon = weapon;
	this.sourceData = sourceData;

	if (shooter instanceof EntityPlayer) {
	    this.pickupStatus = MyEntityArrow.PickupStatus.ALLOWED;
	}
    }

    @Override
    protected void arrowHit(EntityLivingBase living) {

    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate() {

	super.onUpdate();

	if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
	    float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
	    this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));
	    this.rotationPitch = (float) (MathHelper.atan2(this.motionY, (double) f) * (180D / Math.PI));
	    this.prevRotationYaw = this.rotationYaw;
	    this.prevRotationPitch = this.rotationPitch;
	}

	BlockPos blockpos = new BlockPos(this.xTile, this.yTile, this.zTile);
	IBlockState iblockstate = this.world.getBlockState(blockpos);
	Block block = iblockstate.getBlock();

	if (iblockstate.getMaterial() != Material.AIR) {
	    AxisAlignedBB axisalignedbb = iblockstate.getCollisionBoundingBox(this.world, blockpos);

	    if (axisalignedbb != Block.NULL_AABB
		    && axisalignedbb.offset(blockpos).contains(new Vec3d(this.posX, this.posY, this.posZ))) {
		this.inGround = true;
	    }
	}

	if (this.arrowShake > 0) {
	    --this.arrowShake;
	}

	if (this.inGround) {
	    int j = block.getMetaFromState(iblockstate);

	    if ((block != this.inTile || j != this.inData)
		    && !this.world.collidesWithAnyBlock(this.getEntityBoundingBox().grow(0.05D))) {
		this.inGround = false;
		this.motionX *= (double) (this.rand.nextFloat() * 0.2F);
		this.motionY *= (double) (this.rand.nextFloat() * 0.2F);
		this.motionZ *= (double) (this.rand.nextFloat() * 0.2F);
		this.ticksInGround = 0;
		this.ticksInAir = 0;
	    } else {
		++this.ticksInGround;

		if (this.ticksInGround >= 30) {
		    this.setDead();
		}
	    }

	    ++this.timeInGround;
	} else {
	    this.timeInGround = 0;
	    ++this.ticksInAir;
	    Vec3d vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
	    Vec3d vec3d = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
	    RayTraceResult raytraceresult = this.world.rayTraceBlocks(vec3d1, vec3d, false, true, false);
	    vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
	    vec3d = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

	    if (raytraceresult != null) {
		vec3d = new Vec3d(raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);
	    }

	    Entity entity = this.findEntityOnPath(vec3d1, vec3d);

	    if (entity != null) {
		raytraceresult = new RayTraceResult(entity);
	    }

	    if (raytraceresult != null && raytraceresult.entityHit instanceof EntityPlayer) {
		EntityPlayer entityplayer = (EntityPlayer) raytraceresult.entityHit;

		if (this.shootingEntity instanceof EntityPlayer
			&& !((EntityPlayer) this.shootingEntity).canAttackPlayer(entityplayer)) {
		    raytraceresult = null;
		}
	    }

	    if (raytraceresult != null
		    && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
		this.onHit(raytraceresult);
	    }

	    if (this.getIsCritical()) {
		for (int k = 0; k < 4; ++k) {
		    this.world.spawnParticle(EnumParticleTypes.CRIT, this.posX + this.motionX * (double) k / 4.0D,
			    this.posY + this.motionY * (double) k / 4.0D, this.posZ + this.motionZ * (double) k / 4.0D,
			    -this.motionX, -this.motionY + 0.2D, -this.motionZ);
		}
	    }

	    this.posX += this.motionX;
	    this.posY += this.motionY;
	    this.posZ += this.motionZ;
	    float f4 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
	    this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));

	    for (this.rotationPitch = (float) (MathHelper.atan2(this.motionY, (double) f4)
		    * (180D / Math.PI)); this.rotationPitch
			    - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
		;
	    }

	    while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
		this.prevRotationPitch += 360.0F;
	    }

	    while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
		this.prevRotationYaw -= 360.0F;
	    }

	    while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
		this.prevRotationYaw += 360.0F;
	    }

	    this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
	    this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
	    float f1 = 0.99F;
	    float f2 = 0.05F;

	    if (this.isInWater()) {
		for (int i = 0; i < 4; ++i) {
		    float f3 = 0.25F;
		    this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * 0.25D,
			    this.posY - this.motionY * 0.25D, this.posZ - this.motionZ * 0.25D, this.motionX,
			    this.motionY, this.motionZ);
		}

		f1 = 0.6F;
	    }

	    if (this.isWet()) {
		this.extinguish();
	    }

	    this.motionX *= (double) f1;
	    this.motionY *= (double) f1;
	    this.motionZ *= (double) f1;

	    if (!this.hasNoGravity()) {
		this.motionY -= 0.05000000074505806D;
	    }

	    this.setPosition(this.posX, this.posY, this.posZ);
	    this.doBlockCollisions();
	}
    }

    /**
     * Called when the arrow hits a block or an entity
     */
    @Override
    protected void onHit(RayTraceResult raytraceResultIn) {
	try {
	    Entity entity = raytraceResultIn.entityHit;

	    if (entity instanceof EntityLivingBase) {
		float f = MathHelper
			.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
		int i = MathHelper.ceil((double) f * this.damage);

		if (this.getIsCritical()) {
		    i += this.rand.nextInt(i / 2 + 2);
		}

		//

		if (this.shootingEntity instanceof EntityLivingBase) {

		    EntityLivingBase entitylivingbase = (EntityLivingBase) entity;

		    if (!this.world.isRemote) {

			if (!entity.equals(this.shootingEntity)) {

			    this.setDead();

			    UnitData sourceUnit = Load.Unit(this.shootingEntity);

			    sourceUnit.attackWithWeapon((EntityLivingBase) this.shootingEntity, entitylivingbase,
				    this.weapon);

			}

			entitylivingbase.setArrowCountInEntity(entitylivingbase.getArrowCountInEntity() + 1);
		    }

		    if (this.knockbackStrength > 0) {
			float f1 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

			if (f1 > 0.0F) {
			    entitylivingbase.addVelocity(
				    this.motionX * (double) this.knockbackStrength * 0.6000000238418579D / (double) f1,
				    0.1D,
				    this.motionZ * (double) this.knockbackStrength * 0.6000000238418579D / (double) f1);
			}
		    }

		    if (this.shootingEntity instanceof EntityLivingBase) {
			EnchantmentHelper.applyThornEnchantments(entitylivingbase, this.shootingEntity);
			EnchantmentHelper.applyArthropodEnchantments((EntityLivingBase) this.shootingEntity,
				entitylivingbase);
		    }

		    this.arrowHit(entitylivingbase);

		    if (this.shootingEntity != null && entitylivingbase != this.shootingEntity
			    && entitylivingbase instanceof EntityPlayer
			    && this.shootingEntity instanceof EntityPlayerMP) {
			((EntityPlayerMP) this.shootingEntity).connection
				.sendPacket(new SPacketChangeGameState(6, 0.0F));
		    }

		    this.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));

		} else {
		    this.motionX *= -0.10000000149011612D;
		    this.motionY *= -0.10000000149011612D;
		    this.motionZ *= -0.10000000149011612D;
		    this.rotationYaw += 180.0F;
		    this.prevRotationYaw += 180.0F;
		    this.ticksInAir = 0;

		    if (!this.world.isRemote && this.motionX * this.motionX + this.motionY * this.motionY
			    + this.motionZ * this.motionZ < 0.0010000000474974513D) {
			if (this.pickupStatus == MyEntityArrow.PickupStatus.ALLOWED) {
			    this.entityDropItem(this.getArrowStack(), 0.1F);
			}

			this.setDead();
		    }
		}
	    } else {
		BlockPos blockpos = raytraceResultIn.getBlockPos();
		if (blockpos != null) {
		    this.xTile = blockpos.getX();
		    this.yTile = blockpos.getY();
		    this.zTile = blockpos.getZ();
		    IBlockState iblockstate = this.world.getBlockState(blockpos);
		    this.inTile = iblockstate.getBlock();
		    this.inData = this.inTile.getMetaFromState(iblockstate);
		    this.motionX = (double) ((float) (raytraceResultIn.hitVec.x - this.posX));
		    this.motionY = (double) ((float) (raytraceResultIn.hitVec.y - this.posY));
		    this.motionZ = (double) ((float) (raytraceResultIn.hitVec.z - this.posZ));
		    float f2 = MathHelper.sqrt(
			    this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
		    this.posX -= this.motionX / (double) f2 * 0.05000000074505806D;
		    this.posY -= this.motionY / (double) f2 * 0.05000000074505806D;
		    this.posZ -= this.motionZ / (double) f2 * 0.05000000074505806D;
		    this.inGround = true;
		    this.arrowShake = 7;
		    this.setIsCritical(false);

		    if (iblockstate.getMaterial() != Material.AIR) {
			this.inTile.onEntityCollidedWithBlock(this.world, blockpos, iblockstate, this);
		    }
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    protected ItemStack getArrowStack() {
	return ItemStack.EMPTY;
    }

}