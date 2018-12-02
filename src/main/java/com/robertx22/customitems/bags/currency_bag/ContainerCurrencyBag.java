package com.robertx22.customitems.bags.currency_bag;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerCurrencyBag extends Container {

	private final InventoryCurrencyBag currencyBagInv;
	private final int size = 16;

	public ContainerCurrencyBag(InventoryPlayer playerInv, InventoryCurrencyBag flowerBagInv) {
		int i;
		int j;

		this.currencyBagInv = flowerBagInv;

		for (i = 0; i < 2; ++i)
			for (j = 0; j < 8; ++j) {
				int k = j + i * 8;
				addSlotToContainer(new SlotCurrency(flowerBagInv, k, 17 + j * 18, 26 + i * 18));
			}

		for (i = 0; i < 3; ++i)
			for (j = 0; j < 9; ++j)
				addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

		for (i = 0; i < 9; ++i) {

			addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 142));

		}

	}

	public class SlotCurrency extends SlotItemHandler {

		public SlotCurrency(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
			super(itemHandler, index, xPosition, yPosition);

		}

		// if this function returns false, the player won't be able to insert the given
		// item into this slot
		@Override
		public boolean isItemValid(ItemStack stack) {
			return ItemCurrencyBag.IsValidItem(stack);
		}
	}

	@Override
	public boolean canInteractWith(@Nonnull EntityPlayer player) {
		return player.getHeldItemMainhand() == currencyBagInv.bag || player.getHeldItemOffhand() == currencyBagInv.bag;
	}

	@Nonnull
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(slotIndex);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (slotIndex < this.size) {
				if (!mergeItemStack(itemstack1, this.size, 52, true))
					return ItemStack.EMPTY;
			} else {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty())
				slot.putStack(ItemStack.EMPTY);
			else
				slot.onSlotChanged();

			if (itemstack1.getCount() == itemstack.getCount())
				return ItemStack.EMPTY;

			slot.onTake(player, itemstack1);
		}

		return itemstack;
	}
}