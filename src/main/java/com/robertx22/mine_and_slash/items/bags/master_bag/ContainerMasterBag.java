package com.robertx22.mine_and_slash.items.bags.master_bag;

import com.robertx22.mine_and_slash.blocks.slots.handlerslots.SlotHandler;
import com.robertx22.mine_and_slash.items.bags.BaseSlot;
import com.robertx22.mine_and_slash.uncommon.item_filters.bases.ItemFilterGroup;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ContainerTypeRegisters;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

public class ContainerMasterBag extends Container {

    public enum ItemType {
        GEAR(0, ItemFilterGroup.ANY_GEAR_EXCEPT_UNIQUE),
        SPELL(1, ItemFilterGroup.ANY_SPELL),
        CURRENCY(2, ItemFilterGroup.ANY_CURRENCY),
        UNIQUE(3, ItemFilterGroup.ANY_UNIQUE),
        RUNE(4, ItemFilterGroup.ANY_RUNE),
        MAP(5, ItemFilterGroup.ANY_MAP),
        AWAKEN(6, ItemFilterGroup.ANY_AWAKEN),
        ORES(7, ItemFilterGroup.ANY_ORE);

        ItemType(int place, ItemFilterGroup filter) {
            this.place = place;
            this.filter = filter;
        }

        int place = 0;

        ItemFilterGroup filter;

    }

    public ContainerMasterBag(int i, PlayerInventory playerInventory, PacketBuffer pkt) {

        this(i, playerInventory, new InventoryMasterBag(new ItemStack(ItemMasterBag.ITEM), ItemType.GEAR), ItemType
                .valueOf(pkt.readString()));

    }

    public ItemType type = ItemType.GEAR;
    public int bagHash;

    public ContainerMasterBag(int num, PlayerInventory playerInv,
                              InventoryMasterBag basebag, ItemType type) {
        super(ContainerTypeRegisters.MASTER_BAG, num);

        bagHash = basebag.bag.hashCode();

        this.numRows = 6;
        this.size *= ItemType.values().length;

        this.type = type;

        this.inventory = basebag;

        int i = (this.numRows - 4) * 18;

        for (int j = 0; j < this.numRows; ++j) {
            for (int k = 0; k < 9; ++k) {
                this.addSlot(this.slot(inventory, (k + j * 9), 8 + k * 18, 18 + j * 18));
            }
        }

        for (int l = 0; l < 3; ++l) {
            for (int j1 = 0; j1 < 9; ++j1) {
                this.addSlot(new Slot(playerInv, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + i));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(playerInv, i1, 8 + i1 * 18, 161 + i));
        }

    }

    public BaseSlot slot(IItemHandler inv, int index, int x, int y) {
        return new SlotHandler(inv, index, x, y, type.filter);
    }

    public InventoryMasterBag inventory;

    public int size = 9 * 6;
    public int numRows = 6;

    @Override
    public boolean canInteractWith(@Nonnull PlayerEntity player) {
        ItemStack held = player.getHeldItemMainhand();

        return held == this.inventory.bag && this.inventory.bag.isEmpty() == false && held
                .hashCode() == this.bagHash && held.getItem() instanceof ItemMasterBag;
    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < this.numRows * 9) {
                if (!this.mergeItemStack(itemstack1, this.numRows * 9, this.inventorySlots
                        .size(), true)) {
                    slot.onSlotChanged();
                    this.detectAndSendChanges();
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, this.numRows * 9, false)) {
                slot.onSlotChanged();
                this.detectAndSendChanges();
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
