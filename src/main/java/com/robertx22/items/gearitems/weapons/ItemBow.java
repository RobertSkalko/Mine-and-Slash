package com.robertx22.items.gearitems.weapons;

import java.util.HashMap;

import com.robertx22.items.gearitems.bases.BaseWeaponItem;
import com.robertx22.items.gearitems.bases.IWeapon;
import com.robertx22.items.gearitems.bases.WeaponMechanic;
import com.robertx22.items.gearitems.weapon_mechanics.BowWeaponMechanic;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ItemBow extends net.minecraft.item.ItemBow implements IWeapon {
	public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

	public ItemBow() {

	}

	public String Name() {
		return "Bow";
	}

	int arrowCount = 3;

	@Override
	public WeaponMechanic mechanic() {
		return new BowWeaponMechanic();
	}

	// so bow can fire without arrows
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		boolean flag = true; // !this.findAmmo(playerIn).isEmpty();

		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn,
				playerIn, handIn, flag);
		if (ret != null)
			return ret;

		if (BaseWeaponItem.checkDurability(playerIn, itemstack) == false) {
			return flag ? new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack)
					: new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
		} else {
			playerIn.setActiveHand(handIn);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
		}
	}

	// faster loading
	/**
	 * How long it takes to use or consume an item
	 */
	public int getMaxItemUseDuration(ItemStack stack) {
		return 40000;//// 72000;
	}

}
