package com.robertx22.api;

import com.robertx22.spells.bases.MyDamageSource;
import com.robertx22.uncommon.capability.EntityData.UnitData;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class MineAndSlashEvents {

	  public static class GiveExpEvent extends LivingEvent {

	    public int experience = 0;
	    public UnitData playerCapability;

	    public GiveExpEvent(EntityPlayer entity, UnitData playerdata, int exp) {
	      super(entity);
	      playerCapability = playerdata;
	      this.experience = exp;
	      this.setResult(Result.ALLOW);

	    }

	  }

	  /**
	   * Fired when a {@link com.robertx22.uncommon.effectdatas.DamageEffect} is activated.
	   * <p>
	   * Editing the damage source will not affect the damage applied.
	   */
	  public static class DamageEvent extends Event {

	    private final EntityLivingBase source;
	    private final EntityLivingBase target;
	    private final float totalDamage;
	    private final MyDamageSource damageSource;
	    private final boolean fullyBlocked;

	    public DamageEvent(EntityLivingBase source, EntityLivingBase target, float totalDamage, MyDamageSource damageEffect, boolean fullyBlocked) {

	      this.source = source;
	      this.target = target;
	      this.totalDamage = totalDamage;
	      this.damageSource = damageEffect;
	      this.fullyBlocked = fullyBlocked;
	    }

	    public EntityLivingBase getSource() {

	      return this.source;
	    }

	    public EntityLivingBase getTarget() {

	      return this.target;
	    }

	    /**
	     * @return the total damage dealt before blocking
	     */
	    public float getTotalDamage() {

	      return this.totalDamage;
	    }

	    /**
	     * @return the actual damage dealt after blocking
	     */
	    public float getAdjustedDamage() {

	      return this.damageSource.realDamage;
	    }

	    /**
	     * @return the amount of damage blocked
	     */
	    public float getBlockedDamage() {

	      return this.fullyBlocked ? this.totalDamage : this.totalDamage - this.damageSource.realDamage;
	    }

	    /**
	     * @return the damage source
	     */
	    public MyDamageSource getDamageSource() {

	      return this.damageSource;
	    }

	    /**
	     * @return true if all damage was blocked
	     */
	    public boolean isFullyBlocked() {

	      return this.fullyBlocked;
	    }
	  }
	}