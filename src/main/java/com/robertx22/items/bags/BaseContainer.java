package com.robertx22.items.bags;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public abstract class BaseContainer extends Container {

    public BaseInventory inventory;

    public abstract BaseSlot slot(IItemHandler inv, int index, int x, int y);

    public static int size = 9 * 6;
    public static int numRows = 6;

    public BaseContainer(InventoryPlayer playerInv, BaseInventory basebag) {

	this.inventory = basebag;

	int i = (this.numRows - 4) * 18;

	for (int j = 0; j < this.numRows; ++j) {
	    for (int k = 0; k < 9; ++k) {
		this.addSlotToContainer(this.slot(inventory, k + j * 9, 8 + k * 18, 18 + j * 18));
	    }
	}

	for (int l = 0; l < 3; ++l) {
	    for (int j1 = 0; j1 < 9; ++j1) {
		this.addSlotToContainer(new Slot(playerInv, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + i));
	    }
	}

	for (int i1 = 0; i1 < 9; ++i1) {
	    this.addSlotToContainer(new Slot(playerInv, i1, 8 + i1 * 18, 161 + i));
	}

    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer player) {
	return player.getHeldItemMainhand() == this.inventory.bag || player.getHeldItemOffhand() == this.inventory.bag;
    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
	ItemStack itemstack = ItemStack.EMPTY;
	Slot slot = this.inventorySlots.get(index);

	if (slot != null && slot.getHasStack()) {
	    ItemStack itemstack1 = slot.getStack();
	    itemstack = itemstack1.copy();

	    if (index < this.numRows * 9) {
		if (!this.mergeItemStack(itemstack1, this.numRows * 9, this.inventorySlots.size(), true)) {
		    return ItemStack.EMPTY;
		}
	    } else if (!this.mergeItemStack(itemstack1, 0, this.numRows * 9, false)) {
		return ItemStack.EMPTY;
	    }

	    if (itemstack1.isEmpty()) {
		slot.putStack(ItemStack.EMPTY);
	    } else {
		slot.onSlotChanged();
	    }
	}

	return itemstack;
    }
}