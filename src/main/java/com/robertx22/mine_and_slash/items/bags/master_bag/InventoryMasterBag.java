package com.robertx22.mine_and_slash.items.bags.master_bag;

import com.robertx22.mine_and_slash.items.bags.BaseInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;

public class InventoryMasterBag extends BaseInventory {

    final ContainerMasterBag.ItemType type;

    public InventoryMasterBag(ItemStack stack, ContainerMasterBag.ItemType type) {
        super(stack);
        this.type = type;
        readItemStack();
    }

    @Override
    public void setup() {

    }

    public ItemStack getStack() {
        return stack;
    }

    public void readItemStack() {
        if (stack.getTag() != null && stack.getTag().contains(ItemMasterBag.NBT_ID)) {
            CompoundNBT nbt = (CompoundNBT) stack.getTag().get(ItemMasterBag.NBT_ID);
            readNBT((CompoundNBT) nbt.get(type.name()));
        }
    }

    @Override
    public void writeItemStack() {

        if (stack.hasTag() == false) {
            stack.setTag(new CompoundNBT());
        }
        if (stack.getTag().contains(ItemMasterBag.NBT_ID) == false) {
            stack.getTag().put(ItemMasterBag.NBT_ID, new CompoundNBT());
        }

        if (isEmpty()) {
            CompoundNBT nbt = (CompoundNBT) stack.getTag().get(ItemMasterBag.NBT_ID);
            nbt.put(type.name(), new CompoundNBT());
        } else {
            if (!stack.getTag().contains(type.name())) {
                CompoundNBT nbt = (CompoundNBT) stack.getTag().get(ItemMasterBag.NBT_ID);
                nbt.put(type.name(), new CompoundNBT());
            }
            CompoundNBT nbt = (CompoundNBT) stack.getTag().get(ItemMasterBag.NBT_ID);
            writeNBT(nbt.getCompound(type.name()));
        }
    }

    private void readNBT(CompoundNBT compound) {
        final NonNullList<ItemStack> list = NonNullList.<ItemStack>withSize(getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, list);
        for (int i = 0; i < list.size(); i++) {
            setInventorySlotContents(i, list.get(i));
        }
    }

    private void writeNBT(CompoundNBT compound) {
        final NonNullList<ItemStack> list = NonNullList.<ItemStack>withSize(getSizeInventory(), ItemStack.EMPTY);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, getStackInSlot(i));
        }
        ItemStackHelper.saveAllItems(compound, list, false);
    }
}