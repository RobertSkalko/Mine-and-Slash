package com.robertx22.mine_and_slash.blocks.bases;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public abstract class BaseTileContainer extends Container {

    public int size = 0;

    public BlockPos pos;

    protected BaseTileContainer(int size, @Nullable ContainerType<?> type, int id) {
        super(type, id);
        this.size = size;
    }

    public boolean isPlayerInventory(int index) {
        return index < this.inventorySlots.size() - size;
    }

    public int getPlayerInvStart() {
        return 0;
    }

    public int getPlayerInvEnd() {
        return this.inventorySlots.size() - size;
    }

    public int getContainerStart() {
        return this.inventorySlots.size() - size;
    }

    public int getContainerEnd() {
        return this.inventorySlots.size();
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {

        ItemStack itemstack = ItemStack.EMPTY;

        try {
            Slot slot = this.inventorySlots.get(index);
            if (slot != null && slot.getHasStack()) {
                ItemStack itemstack1 = slot.getStack();
                itemstack = itemstack1.copy();

                if (isPlayerInventory(index)) {
                    if (!this.mergeItemStack(itemstack1, getContainerStart(), getContainerEnd(), true)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.mergeItemStack(itemstack1, getPlayerInvStart(), getPlayerInvEnd(), false)) {
                    return ItemStack.EMPTY;
                }

                if (itemstack1.isEmpty()) {
                    slot.putStack(ItemStack.EMPTY);
                } else {
                    slot.onSlotChanged();
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
            return itemstack;
        }

        return itemstack;
    }

    // detectAndSendChanges is called every tick and can be used to get listeners and update, BUT it would take a huge rewrite and it's not worth it.

}
