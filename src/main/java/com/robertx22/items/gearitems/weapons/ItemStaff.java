package com.robertx22.items.gearitems.weapons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nonnull;

import com.robertx22.items.gearitems.bases.BaseWeaponItem;
import com.robertx22.items.gearitems.bases.IWeapon;
import com.robertx22.items.gearitems.bases.WeaponMechanic;
import com.robertx22.items.gearitems.offhands.IEffectItem;
import com.robertx22.items.gearitems.weapon_mechanics.StaffWeaponMechanic;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.spells.bases.projectile.EntityStaffProjectile;
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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
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
				
				GearItemData weapondata = data.getWeaponData(player);
				
				data.recalculateStats(player, Load.World(player.world));
				
				float multi = 1;

				if (data.tryUseWeapon(weapondata, player, multi)) {
					
					EntityStaffProjectile projectile = new EntityStaffProjectile(world);
					projectile.SetReady(player.getHeldItem(hand));
					projectile.SpawnAndShoot(null, null, player);

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