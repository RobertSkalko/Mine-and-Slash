package com.robertx22.mine_and_slash.blocks.bases;

import com.robertx22.mine_and_slash.items.misc.ItemCapacitor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;
import java.util.Arrays;

public abstract class BaseTile extends TileEntity implements IOBlock, ISidedInventory, ITickableTileEntity, INamedContainerProvider {

    public BaseTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    protected ItemStack[] itemStacks;

    public int ticks = 0;
    public short cookTime = 0;
    public int fuel = 0;

    public abstract int ticksRequired();

    public abstract void finishCooking();

    public abstract boolean isCooking();

    public abstract int tickRate();

    public abstract void doActionEveryTime();

    @Override
    public void tick() {
        if (!this.world.isRemote) {

            ticks++;
            if (ticks > tickRate()) {
                ticks = 0;

                doActionEveryTime();

                if (isCooking()) {

                    cookTime += tickRate();

                    if (cookTime >= ticksRequired()) {
                        finishCooking();
                        cookTime = 0;
                    }

                } else {
                    cookTime = 0;
                }

            }
        }

    }

    private static final short COOK_TIME_FOR_COMPLETION = 200; // vanilla value is 200 = 10 seconds

    public double fractionOfCookTimeComplete() {
        double fraction = cookTime / (double) COOK_TIME_FOR_COMPLETION;
        return MathHelper.clamp(fraction, 0.0, 1.0);
    }

    // OVERRIDE IF AUTOMATABLE
    @Override
    public int[] inputSlots() {
        return new int[0];
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
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
    public boolean canInsertItem(int index, ItemStack itemStackIn, Direction direction) {

        if (this.isAutomatable() && containsSlot(index, this.inputSlots())) {
            // don't insert shit
            return this.isItemValidInput(itemStackIn);
        }
        return false;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, Direction direction) {

        if (this.isAutomatable()) {
            // don't extract stuff that's being processed

            if (stack.getItem() instanceof ItemCapacitor) {
                return false; // temp fix
            }

            Boolean isValidInput = this.isItemValidInput(stack);

            return isValidInput == false;
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
            itemStackRemoved = itemStackInSlot.split(count);
            if (itemStackInSlot.getCount() == 0) { // getStackSize
                setInventorySlotContents(slotIndex, ItemStack.EMPTY); // EMPTY_ITEM
            }
        }
        markDirty();
        return itemStackRemoved;
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
    public void openInventory(PlayerEntity player) {
    }

    @Override
    public void closeInventory(PlayerEntity player) {
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
    public boolean isUsableByPlayer(PlayerEntity player) {
        if (this.world.getTileEntity(this.pos) != this)
            return false;
        final double X_CENTRE_OFFSET = 0.5;
        final double Y_CENTRE_OFFSET = 0.5;
        final double Z_CENTRE_OFFSET = 0.5;
        final double MAXIMUM_DISTANCE_SQ = 8.0 * 8.0;
        return player.getDistanceSq(pos.getX() + X_CENTRE_OFFSET, pos.getY() + Y_CENTRE_OFFSET, pos
                .getZ() + Z_CENTRE_OFFSET) < MAXIMUM_DISTANCE_SQ;
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

    @Override
    public CompoundNBT write(CompoundNBT parentNBTTagCompound) {
        super.write(parentNBTTagCompound); // The super call is required to save and load the tiles location

        ListNBT dataForAllSlots = new ListNBT();
        for (int i = 0; i < this.itemStacks.length; ++i) {
            if (!this.itemStacks[i].isEmpty()) { // isEmpty()
                CompoundNBT dataForThisSlot = new CompoundNBT();
                dataForThisSlot.putByte("Slot", (byte) i);
                this.itemStacks[i].write(dataForThisSlot);
                dataForAllSlots.add(dataForThisSlot);
            }
        }
        // the array of hashmaps is then inserted into the instance hashmap for the
        // container
        parentNBTTagCompound.put("Items", dataForAllSlots);
        parentNBTTagCompound.putInt("ticks", ticks);

        // Save everything else
        parentNBTTagCompound.putShort("CookTime", cookTime);

        parentNBTTagCompound.putInt("fuel", this.fuel);
        return parentNBTTagCompound;
    }

    // This is where you load the dataInstance that you saved in write
    @Override
    public void read(CompoundNBT nbtTagCompound) {
        super.read(nbtTagCompound); // The super call is required to save and load the tiles location
        final byte NBT_TYPE_COMPOUND = 10; // See NBTBase.createNewByType() for a listing
        ListNBT dataForAllSlots = nbtTagCompound.getList("Items", NBT_TYPE_COMPOUND);

        Arrays.fill(itemStacks, ItemStack.EMPTY); // set all slots to empty EMPTY_ITEM
        for (int i = 0; i < dataForAllSlots.size(); ++i) {
            CompoundNBT dataForOneSlot = dataForAllSlots.getCompound(i);
            byte slotNumber = dataForOneSlot.getByte("Slot");
            if (slotNumber >= 0 && slotNumber < this.itemStacks.length) {
                this.itemStacks[slotNumber] = ItemStack.read(dataForOneSlot);
            }
        }

        // Load everything else. Trim the arrays (or pad with 0) to make sure they have
        // the correct number of elements
        cookTime = nbtTagCompound.getShort("CookTime");
        ticks = nbtTagCompound.getInt("ticks");
        this.fuel = nbtTagCompound.getInt("fuel");
    }

    //	// When the world loads from disk, the server needs to send the TileEntity information to the client
    //	//  it uses getUpdatePacket(), getUpdateTag(), onDataPacket(), and handleUpdateTag() to do this
    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT updateTagDescribingTileEntityState = getUpdateTag();
        final int METADATA = 0;
        return new SUpdateTileEntityPacket(this.pos, METADATA, updateTagDescribingTileEntityState);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        CompoundNBT updateTagDescribingTileEntityState = pkt.getNbtCompound();
        handleUpdateTag(updateTagDescribingTileEntityState);
    }

    /*
     * Creates a tag containing the TileEntity information, used by vanilla to
     * transmit from server to client Warning - although our getUpdatePacket() uses
     * this method, vanilla also calls it directly, so don't remove it.
     */
    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbtTagCompound = new CompoundNBT();
        write(nbtTagCompound);
        return nbtTagCompound;
    }

    /*
     * Populates this TileEntity with information from the tag, used by vanilla to
     * transmit from server to client Warning - although our onDataPacket() uses
     * this method, vanilla also calls it directly, so don't remove it.
     */
    @Override
    public void handleUpdateTag(CompoundNBT tag) {
        this.read(tag);
    }
    // ------------------------

}
