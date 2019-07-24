package com.robertx22.mine_and_slash.items.bags.master_bag;

import com.robertx22.mine_and_slash.uncommon.capability.MasterLootBagCap;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class InventoryMasterBag implements IItemHandlerModifiable {

    final public ItemStack bag;
    public ItemStackHandler bagInv;

    public InventoryMasterBag(ItemStack bag, ContainerMasterBag.ItemType type) {
        this.bag = bag;

        MasterLootBagCap.IMasterLootBagData fullInv = bag.getCapability(MasterLootBagCap.Data)
                .orElse(null);

        this.bagInv = new ItemStackHandler(6 * 9);

        if (fullInv != null) {

            this.bagInv = fullInv.getInventory(type);

        }

    }

    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
        bagInv.setStackInSlot(slot, stack);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        return bagInv.isItemValid(slot, stack);
    }

    @Override
    public int getSlots() {
        return bagInv.getSlots();
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return bagInv.getStackInSlot(slot);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return bagInv.insertItem(slot, stack, simulate);
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return bagInv.extractItem(slot, amount, simulate);
    }

    @Override
    public int getSlotLimit(int slot) {
        return bagInv.getSlotLimit(slot);
    }
}