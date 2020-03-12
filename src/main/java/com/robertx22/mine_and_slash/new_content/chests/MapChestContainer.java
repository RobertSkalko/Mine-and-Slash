package com.robertx22.mine_and_slash.new_content.chests;

import com.robertx22.mine_and_slash.mmorpg.registers.common.ModContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class MapChestContainer extends Container {

    private final IInventory inventory;

    public static int SIZE = 27;
    static int rowLength = 9;
    static int rowCount = 3;

    public MapChestContainer(int i, PlayerInventory playerInventory,
                             PacketBuffer packetBuffer) {
        this(i, playerInventory, new Inventory(SIZE));

    }

    public MapChestContainer(int windowId, PlayerInventory playerInventory, IInventory inventory) {
        super(ModContainers.MAP_CHEST.get(), windowId);
        //assertInventorySize(inventory, 27);

        this.inventory = inventory;

        inventory.openInventory(playerInventory.player);

        // COPIED FROM SHULKER BOX
        int xpos;
        int ypos;
        for (xpos = 0; xpos < 3; ++xpos) {
            for (ypos = 0; ypos < 9; ++ypos) {
                this.addSlot(new Slot(inventory, ypos + xpos * 9, 8 + ypos * 18, 18 + xpos * 18));
            }
        }
        for (xpos = 0; xpos < 3; ++xpos) {
            for (ypos = 0; ypos < 9; ++ypos) {
                this.addSlot(new Slot(playerInventory, ypos + xpos * 9 + 9, 8 + ypos * 18, 84 + xpos * 18));
            }
        }
        for (xpos = 0; xpos < 9; ++xpos) {
            this.addSlot(new Slot(playerInventory, xpos, 8 + xpos * 18, 142));
        }

    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return this.inventory.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < SIZE) {
                if (!this.mergeItemStack(itemstack1, SIZE, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, SIZE, false)) {
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

    @Override
    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.inventory.closeInventory(playerIn);
    }

}