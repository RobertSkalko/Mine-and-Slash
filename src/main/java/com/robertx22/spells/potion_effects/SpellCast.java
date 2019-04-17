package com.robertx22.spells.potion_effects;

import java.lang.ref.WeakReference;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class SpellCast {

    private SpellPotionBase potion;
    private WeakReference<Entity> applier;
    private WeakReference<Entity> caster;
    private NBTTagCompound data;
    private UUID casterUUID;
    private UUID applierUUID;

    public SpellCast(SpellPotionBase potion, Entity caster) {
	this.potion = potion;
	this.applier = new WeakReference<>(caster);
	this.caster = new WeakReference<>(caster);
	this.data = new NBTTagCompound();
	if (caster instanceof EntityPlayer) {
	    this.casterUUID = caster.getUniqueID();
	    this.applierUUID = caster.getUniqueID();
	}
    }

    public SpellPotionBase getPotion() {
	return potion;
    }

    void updateRefs(World world) {
	if (casterUUID != null) {
	    caster = new WeakReference<>(world.getPlayerEntityByUUID(casterUUID));
	}
	if (applierUUID != null) {
	    applier = new WeakReference<>(world.getPlayerEntityByUUID(applierUUID));
	}
    }

    public Entity getApplier() {
	return applier.get();
    }

    public Entity getCaster() {
	return caster.get();
    }
}