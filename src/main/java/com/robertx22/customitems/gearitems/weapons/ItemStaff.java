package com.robertx22.customitems.gearitems.weapons;

import java.util.HashMap;

import javax.annotation.Nonnull;

import com.robertx22.customitems.gearitems.bases.BaseWeaponItem;
import com.robertx22.customitems.gearitems.bases.IWeapon;
import com.robertx22.customitems.gearitems.bases.WeaponMechanic;
import com.robertx22.customitems.gearitems.weapon_mechanics.StaffWeaponMechanic;
import com.robertx22.spells.EntityStaffProjectileNormal;
import com.robertx22.spells.aoe_projectile.AcidExplosion.EffectAcidExplosion;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.SoundUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemStaff extends BaseWeaponItem implements IWeapon {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public ItemStaff() {

    }

    @Override
    public String Name() {
	return "Staff";
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {

	player.swingArm(hand);

	try {

	    if (!world.isRemote) {

	    }
	    if (checkDurability(player, player.getHeldItem(hand))) {

		UnitData data = Load.Unit(player);

		data.recalculateStats(player, Load.World(player.world));

		if (data.tryUseWeapon(player, this.mechanic(), player.getHeldItem(hand))) {

		    EntityStaffProjectileNormal projectile = new EntityStaffProjectileNormal(world, player);
		    projectile.SetReady(player.getHeldItem(hand));
		    projectile.SpawnAndShoot(new EffectAcidExplosion(), player);

		    player.getHeldItem(hand).damageItem(1, player);

		    SoundUtils.playSoundAtPlayer(player, SoundEvents.ENTITY_SNOWBALL_THROW, 1, 1);

		}

	    }
	} catch (

	Exception e) {
	    e.printStackTrace();
	}

	return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
    }

    @Override
    public WeaponMechanic mechanic() {
	return new StaffWeaponMechanic();
    }
}