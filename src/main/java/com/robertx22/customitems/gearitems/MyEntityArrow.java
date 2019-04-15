package com.robertx22.customitems.gearitems;

import com.robertx22.uncommon.capability.EntityData.UnitData;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.RayTraceResult;
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