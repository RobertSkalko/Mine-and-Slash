package com.robertx22.customitems.gearitems;

import java.util.List;

import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MyEntityArrow extends EntityArrow {

    ItemStack weapon = ItemStack.EMPTY;
    UnitData sourceData = null;

    private static final DataParameter<Byte> CRITICAL = EntityDataManager.<Byte>createKey(EntityArrow.class,
	    DataSerializers.BYTE);
    private int xTile;
    private int yTile;
    private int zTile;
    private Block inTile;
    private int inData;
    protected boolean inGround;
    protected int timeInGround;
    /** 1 if the player can pick up the arrow */
    public EntityArrow.PickupStatus pickupStatus;
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

	this.pickupStatus = MyEntityArrow.PickupStatus.DISALLOWED;

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

    @Override
    public void onUpdate() {
	// super.onUpdate();

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

		if (this.ticksInGround >= 1200) {
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

	    List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this,
		    this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ));
	    double d0 = 0.0D;
	    boolean flag = false;

	    for (int i = 0; i < list.size(); ++i) {
		Entity entity = list.get(i);

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
		if (entity instanceof EntityLivingBase && entity.hasCapability(EntityData.Data, null)) {

		    if (raytraceresult != null
			    && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {

			this.onHit(raytraceresult);
		    }

		}
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

	    if (entity instanceof EntityLivingBase) { // apparenty ender dragons are sometimes not entityliving?

		if (sourceData != null) {

		    if (entity.equals(this.shootingEntity)) {
			return; // this fixes arrows spawning on player..
		    }

		    if (this.shootingEntity instanceof EntityLivingBase) {

			EntityLivingBase entitylivingbase = (EntityLivingBase) entity;

			if (!this.world.isRemote) {

			    this.setDead();

			    // super.onHit(raytraceResultIn);

			    sourceData.attackWithWeapon((EntityLivingBase) this.shootingEntity, entitylivingbase,
				    this.weapon);

			    entitylivingbase.setArrowCountInEntity(entitylivingbase.getArrowCountInEntity() + 1);
			}

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