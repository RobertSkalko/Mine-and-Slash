package com.robertx22.customitems.gearitems.bases;

import baubles.api.IBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public abstract class BaseBaublesItem extends Item implements IBauble, IGearItem {

    public BaseBaublesItem() {

	this.setMaxStackSize(1);
	this.setMaxDamage(0);

    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
	// CAUSE DUPE GLITCH

	/*
	 * if (!world.isRemote) { IBaublesItemHandler baubles =
	 * BaublesApi.getBaublesHandler(player); for (int i = 0; i < baubles.getSlots();
	 * i++) if ((baubles.getStackInSlot(i) == null ||
	 * baubles.getStackInSlot(i).isEmpty()) && baubles.isItemValidForSlot(i,
	 * player.getHeldItem(hand), player)) { baubles.setStackInSlot(i,
	 * player.getHeldItem(hand).copy()); if (!player.capabilities.isCreativeMode) {
	 * player.inventory.setInventorySlotContents(player.inventory.currentItem,
	 * ItemStack.EMPTY); } onEquipped(player.getHeldItem(hand), player); break; } }
	 */
	return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
	player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, .75F, 1.9f);
    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
	player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, .75F, 2f);
    }

}
