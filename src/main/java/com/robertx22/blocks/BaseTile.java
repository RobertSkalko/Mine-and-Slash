package com.robertx22.blocks;

import java.util.Arrays;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;

public abstract class BaseTile extends TileEntity implements IOBlock, ISidedInventory, ITickable {

    protected ItemStack[] itemStacks;

    // OVERRIDE IF AUTOMATABLE
    @Override
    public int[] inputSlots() {
	return new int[0];
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
	return slots();
    }

    @Override
    public boolean isItemValidOutput(ItemStack stack) {
	return true;
    }

    private int[] slots() {

	int[] ints = new int[this.itemStacks.length];

	for (int i = 0; i < itemStacks.length; i++) {
	    ints[i] = i;
	}

	return ints;
    }

    private boolean containsSlot(int index, int[] slots) {

	for (int i : this.inputSlots()) {
	    if (i == index) {
		return true;
	    }
	}
	return false;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {

	if (this.isAutomatable() && containsSlot(index, this.inputSlots())) {
	    // don't insert shit
	    return this.isItemValidInput(itemStackIn);
	}
	return false;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {

	if (this.isAutomatable()) {
	    // don't extract stuff that's being processed
	    return this.isItemValidInput(stack) == false;
	}
	return false;
    }

    // Gets the stack in the given slot
    @Override
    public ItemStack getStackInSlot(int i) {

	return itemStacks[i];
    }

    /**
     * Removes some of the units from itemstack in the given slot, and returns as a
     * separate itemstack
     * 
     * @param slotIndex the slot number to remove the items from
     * @param count     the number of units to remove
     * @return a new itemstack containing the units removed from the slot
     */
    @Override
    public ItemStack decrStackSize(int slotIndex, int count) {
	ItemStack itemStackInSlot = getStackInSlot(slotIndex);
	if (itemStackInSlot.isEmpty())
	    return ItemStack.EMPTY; // isEmpty(), EMPTY_ITEM

	ItemStack itemStackRemoved;
	if (itemStackInSlot.getCount() <= count) { // getStackSize
	    itemStackRemoved = itemStackInSlot;
	    setInventorySlotContents(slotIndex, ItemStack.EMPTY); // EMPTY_ITEM
	} else {
	    itemStackRemoved = itemStackInSlot.splitStack(count);
	    if (itemStackInSlot.getCount() == 0) { // getStackSize
		setInventorySlotContents(slotIndex, ItemStack.EMPTY); // EMPTY_ITEM
	    }
	}
	markDirty();
	return itemStackRemoved;
    }

    // returns the number of ticks the given item will burn. Returns 0 if the given
    // item is not a valid fuel
    public static short getItemBurnTime(ItemStack stack) {
	int burntime = TileEntityFurnace.getItemBurnTime(stack); // just use the vanilla values
	return (short) MathHelper.clamp(burntime, 0, Short.MAX_VALUE);
    }

    // Gets the number of slots in the inventory
    @Override
    public int getSizeInventory() {
	return itemStacks.length;
    }

    // returns true if all of the slots in the inventory are empty
    @Override
    public boolean isEmpty() {
	for (ItemStack itemstack : itemStacks) {
	    if (!itemstack.isEmpty()) { // isEmpty()
		return false;
	    }
	}

	return true;
    }

    // overwrites the stack in the given slotIndex with the given stack
    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemstack) {
	itemStacks[slotIndex] = itemstack;
	if (!itemstack.isEmpty() && itemstack.getCount() > getInventoryStackLimit()) { // isEmpty(); getStackSize()
	    itemstack.setCount(getInventoryStackLimit()); // setStackSize()
	}
	markDirty();
    }

    // set all slots to empty
    @Override
    public void clear() {
	Arrays.fill(itemStacks, ItemStack.EMPTY); // EMPTY_ITEM
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    // -----------------------------------------------------------------------------------------------------------
    // The following methods are not needed for this example but are part of
    // IInventory so they must be implemented

    // Unused unless your container specifically uses it.
    // Return true if the given stack is allowed to go in the given slot
    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemstack) {
	return true;
    }

    /**
     * This method removes the entire contents of the given slot and returns it.
     * Used by containers such as crafting tables which return any items in their
     * slots when you close the GUI
     * 
     * @param slotIndex
     * @return
     */
    @Override
    public ItemStack removeStackFromSlot(int slotIndex) {

	ItemStack itemStack = getStackInSlot(slotIndex);
	if (!itemStack.isEmpty())
	    setInventorySlotContents(slotIndex, ItemStack.EMPTY); // isEmpty(); EMPTY_ITEM
	return itemStack;
    }

    @Override
    public int getInventoryStackLimit() {
	return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
	if (this.world.getTileEntity(this.pos) != this)
	    return false;
	final double X_CENTRE_OFFSET = 0.5;
	final double Y_CENTRE_OFFSET = 0.5;
	final double Z_CENTRE_OFFSET = 0.5;
	final double MAXIMUM_DISTANCE_SQ = 8.0 * 8.0;
	return player.getDistanceSq(pos.getX() + X_CENTRE_OFFSET, pos.getY() + Y_CENTRE_OFFSET,
		pos.getZ() + Z_CENTRE_OFFSET) < MAXIMUM_DISTANCE_SQ;
    }

    static public boolean isItemValidForFuelSlot(ItemStack itemStack) {
	return true;
    }

    static public boolean isItemValidForInputSlot(ItemStack itemStack) {
	return true;
    }

    static public boolean isItemValidForOutputSlot(ItemStack itemStack) {
	return false;
    }
}
