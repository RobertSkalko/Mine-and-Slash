package com.robertx22.customitems.loot_bag;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerLootBag extends Container {

    private final InventoryLootBag lootBagInv;
    public static int size = 9 * 6;
    public static int numRows = 6;

    public ContainerLootBag(InventoryPlayer playerInv, InventoryLootBag flowerBagInv) {

	this.lootBagInv = flowerBagInv;

	int i = (this.numRows - 4) * 18;

	for (int j = 0; j < this.numRows; ++j) {
	    for (int k = 0; k < 9; ++k) {
		this.addSlotToContainer(new SlotLoot(flowerBagInv, k + j * 9, 8 + k * 18, 18 + j * 18));
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

    public class SlotLoot extends SlotItemHandler {

	public SlotLoot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
	    super(itemHandler, index, xPosition, yPosition);

	}

	// if this function returns false, the player won't be able to insert the given
	// item into this slot
	@Override
	public boolean isItemValid(ItemStack stack) {
	    return ItemLootBag.IsValidItem(stack);
	}
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer player) {
	return player.getHeldItemMainhand() == lootBagInv.bag || player.getHeldItemOffhand() == lootBagInv.bag;
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