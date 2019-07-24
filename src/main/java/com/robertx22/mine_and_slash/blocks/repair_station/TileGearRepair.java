package com.robertx22.mine_and_slash.blocks.repair_station;

import com.robertx22.mine_and_slash.blocks.bases.BaseTile;
import com.robertx22.mine_and_slash.blocks.slots.FuelSlot;
import com.robertx22.mine_and_slash.items.misc.ItemCapacitor;
import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;

public class TileGearRepair extends BaseTile {

    @Override
    public boolean isAutomatable() {
        return false;
    }

    @Override
    public boolean isItemValidInput(ItemStack stack) {
        return getSmeltingResultForItem(stack).isEmpty() == false;
    }

    public int MaximumFuel = 5000;

    // returns the smelting result for the given stack. Returns null if the given
    // stack can not be smelted
    public ItemStack getSmeltingResultForItem(ItemStack stack) {
        GearItemData gear = Gear.Load(stack);
        if (gear != null) {
            ItemStack copy = stack.copy();
            int dmg = copy.getDamage() - fuel;

            if (dmg < 0) {
                dmg = 0;
            }
            copy.setDamage(dmg);

            return copy;
        }
        return ItemStack.EMPTY;

    }

    // IMPORTANT STUFF ABOVE

    // Create and initialize the itemStacks variable that will store store the
    // itemStacks
    public static final int FUEL_SLOTS_COUNT = 1;
    public static final int INPUT_SLOTS_COUNT = 5;
    public static final int OUTPUT_SLOTS_COUNT = 5;
    public static final int TOTAL_SLOTS_COUNT = FUEL_SLOTS_COUNT + INPUT_SLOTS_COUNT + OUTPUT_SLOTS_COUNT + 1;

    public static final int FIRST_FUEL_SLOT = 0;
    public static final int FIRST_INPUT_SLOT = FIRST_FUEL_SLOT + FUEL_SLOTS_COUNT;
    public static final int FIRST_OUTPUT_SLOT = FIRST_INPUT_SLOT + INPUT_SLOTS_COUNT;
    public static final int FIRST_CAPACITOR_SLOT = FIRST_OUTPUT_SLOT + OUTPUT_SLOTS_COUNT;

    private static final short COOK_TIME_FOR_COMPLETION = 200; // vanilla value is 200 = 10 seconds

    public TileGearRepair() {
        super(BlockRegister.GEAR_REPAIR);

        itemStacks = new ItemStack[TOTAL_SLOTS_COUNT];
        clear();
    }

    /**
     * Returns the amount of fuel remaining on the currently burning item in the
     * given fuel slot.
     *
     * @return fraction remaining, between 0 - 1
     * @fuelSlot the number of the fuel slot (0..3)
     */
    public double fractionOfFuelRemaining(int fuelSlot) {
        if (this.fuel <= 0)
            return 0;
        double fraction = fuel / (double) MaximumFuel;
        return MathHelper.clamp(fraction, 0.0, 1.0);
    }

    /**
     * return the remaining burn time of the fuel in the given slot
     *
     * @param fuelSlot the number of the fuel slot (0..3)
     * @return seconds remaining
     */
    public int secondsOfFuelRemaining(int fuelSlot) {
        if (fuel <= 0)
            return 0;
        return fuel; // 20 ticks per second
    }

    /**
     * Returns the amount of cook time completed on the currently cooking item.
     *
     * @return fraction remaining, between 0 - 1
     */
    public double fractionOfCookTimeComplete() {
        double fraction = cookTime / (double) COOK_TIME_FOR_COMPLETION;
        return MathHelper.clamp(fraction, 0.0, 1.0);
    }

    @Override
    public int ticksRequired() {
        return COOK_TIME_FOR_COMPLETION;
    }

    @Override
    public void finishCooking() {
        this.smeltItem();
    }

    @Override
    public boolean isCooking() {

        return canSmelt();
    }

    @Override
    public int tickRate() {
        return 10;
    }

    @Override
    public void doActionEveryTime() {

        this.burnFuel();

    }

    private int burnFuel() {
        int burningCount = 0;
        boolean inventoryChanged = false;
        // Iterate over all the fuel slots
        for (int i = 0; i < FUEL_SLOTS_COUNT; i++) {
            int fuelSlotNumber = i + FIRST_FUEL_SLOT;

            if (this.fuel < this.MaximumFuel) {
                if (!itemStacks[fuelSlotNumber].isEmpty()) { // isEmpty()
                    // If the stack in this slot is not null and is fuel, set burnTimeRemaining &
                    // burnTimeInitialValue to the
                    // item's burn time and decrease the stack size

                    int fuelgained = FuelSlot.FUEL_VALUES.getOrDefault(itemStacks[fuelSlotNumber]
                            .getItem(), 0);

                    if (fuelgained > 0) {
                        fuel += fuelgained;

                        itemStacks[fuelSlotNumber].shrink(1); // decreaseStackSize()
                        ++burningCount;
                        inventoryChanged = true;
                        // If the stack size now equals 0 set the slot contents to the items container
                        // item. This is for fuel
                        // items such as lava buckets so that the bucket is not consumed. If the item
                        // dose not have
                        // a container item getContainerItem returns null which sets the slot contents
                        // to null
                        if (itemStacks[fuelSlotNumber].getCount() == 0) { // getStackSize()
                            itemStacks[fuelSlotNumber] = itemStacks[fuelSlotNumber].getItem()
                                    .getContainerItem(itemStacks[fuelSlotNumber]);
                        }
                    }
                }
            }
        }
        if (inventoryChanged)
            markDirty();
        return burningCount;
    }

    /**
     * Check if any of the input items are smeltable and there is sufficient space
     * in the output slots
     *
     * @return true if smelting is possible
     */
    private boolean canSmelt() {
        return smeltItem(false);
    }

    /**
     * Smelt an input item into an output slot, if possible
     */
    private void smeltItem() {
        smeltItem(true);
    }

    /**
     * checks that there is an item to be smelted in one of the input slots and that
     * there is room for the result in the output slots If desired, performs the
     * smelt
     *
     * @param performSmelt if true, perform the smelt. if false, check whether
     *                     smelting is possible, but don't change the inventory
     * @return false if no items can be smelted, true otherwise
     */
    private boolean smeltItem(boolean performSmelt) {
        if (this.fuel < 1) {
            return false;
        }

        Integer firstSuitableInputSlot = null;
        Integer firstSuitableOutputSlot = null;
        ItemStack result = ItemStack.EMPTY; // EMPTY_ITEM

        int fuelNeeded = 0;
        float fuelMulti = 1F;

        // TODO

        if (!itemStacks[FIRST_CAPACITOR_SLOT].isEmpty()) {

            Item item = itemStacks[FIRST_CAPACITOR_SLOT].getItem();

            if (item instanceof ItemCapacitor) {
                fuelMulti = ((ItemCapacitor) item).GetFuelMultiplier();
                // System.out.println("it works!");
            }

        }

        // finds the first input slot which is smeltable and whose result fits into an
        // output slot (stacking if possible)
        for (int inputSlot = FIRST_INPUT_SLOT; inputSlot < FIRST_INPUT_SLOT + INPUT_SLOTS_COUNT; inputSlot++) {
            if (!itemStacks[inputSlot].isEmpty()) { // isEmpty()

                fuelNeeded = itemStacks[inputSlot].getDamage();

                if (fuelNeeded > this.fuel) {
                    fuelNeeded = this.fuel;
                }

                if (fuelNeeded * fuelMulti <= this.fuel) {
                    result = getSmeltingResultForItem(itemStacks[inputSlot]);

                } else {
                    result = ItemStack.EMPTY;
                }

                if (!result.isEmpty()) { // isEmpty()
                    // find the first suitable output slot- either empty, or with identical item
                    // that has enough space
                    for (int outputSlot = FIRST_OUTPUT_SLOT; outputSlot < FIRST_OUTPUT_SLOT + OUTPUT_SLOTS_COUNT; outputSlot++) {
                        ItemStack outputStack = itemStacks[outputSlot];
                        if (outputStack.isEmpty()) { // isEmpty()
                            firstSuitableInputSlot = inputSlot;
                            firstSuitableOutputSlot = outputSlot;
                            break;
                        }

                        if (outputStack.getItem() == result.getItem()

                                && ItemStack.areItemStackTagsEqual(outputStack, result)) {
                            int combinedSize = itemStacks[outputSlot].getCount() + result.getCount(); // getStackSize()
                            if (combinedSize <= getInventoryStackLimit() && combinedSize <= itemStacks[outputSlot]
                                    .getMaxStackSize()) {
                                firstSuitableInputSlot = inputSlot;
                                firstSuitableOutputSlot = outputSlot;
                                break;
                            }
                        }
                    }
                    if (firstSuitableInputSlot != null)
                        break;
                }
            }
        }

        if (firstSuitableInputSlot == null)
            return false;
        if (!performSmelt)
            return true;

        // alter input and output
        itemStacks[firstSuitableInputSlot].shrink(1); // decreaseStackSize()
        if (itemStacks[firstSuitableInputSlot].getCount() <= 0) {
            itemStacks[firstSuitableInputSlot] = ItemStack.EMPTY; // getStackSize(), EmptyItem
        }
        if (itemStacks[firstSuitableOutputSlot].isEmpty()) { // isEmpty()
            itemStacks[firstSuitableOutputSlot] = result.copy(); // Use deep .copy() to avoid altering the recipe
        } else {
            int newStackSize = itemStacks[firstSuitableOutputSlot].getCount() + result.getCount();
            itemStacks[firstSuitableOutputSlot].setCount(newStackSize); // setStackSize(), getStackSize()
        }

        fuel -= fuelNeeded * fuelMulti; // TODO

        markDirty();
        return true;
    }

    @Override
    public ITextComponent getDisplayName() {
        return CLOC.blank("block.mmorpg.repair_station");

    }

    @Nullable
    @Override
    public Container createMenu(int num, PlayerInventory inventory, PlayerEntity player) {
        return new ContainerGearRepair(num, inventory, this, this.getPos());
    }
}