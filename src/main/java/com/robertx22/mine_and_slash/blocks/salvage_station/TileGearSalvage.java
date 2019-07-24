package com.robertx22.mine_and_slash.blocks.salvage_station;

import com.robertx22.mine_and_slash.blocks.bases.BaseTile;
import com.robertx22.mine_and_slash.items.misc.ItemCapacitor;
import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ISalvagable;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;

public class TileGearSalvage extends BaseTile {

    @Override
    public int[] inputSlots() {
        int[] ints = new int[INPUT_SLOTS_COUNT];
        for (int i = 0; i < INPUT_SLOTS_COUNT; i++) {
            ints[i] = i;
        }

        return ints;
    }

    @Override
    public boolean isAutomatable() {
        return true;
    }

    private float getCapacitorBonus() {

        ItemStack stack = itemStacks[FIRST_CAPACITOR_SLOT];

        if (stack.getItem() instanceof ItemCapacitor) {
            ItemCapacitor cap = (ItemCapacitor) stack.getItem();

            return cap.getSalvageBonusChance();
        }

        return 0;

    }

    public ItemStack getSmeltingResultForItem(ItemStack st) {

        float bonus = getCapacitorBonus();

        ICommonDataItem data = ICommonDataItem.load(st);

        if (data != null) {
            if (data.isSalvagable(ISalvagable.SalvageContext.SALVAGE_STATION)) {
                return data.getSalvageResult(bonus);
            }
        }

        return ItemStack.EMPTY;

    }

    // IMPORTANT STUFF ABOVE

    // Create and initialize the itemStacks variable that will store store the
    // itemStacks
    public static final int INPUT_SLOTS_COUNT = 5;
    public static final int OUTPUT_SLOTS_COUNT = 5;
    public static final int TOTAL_SLOTS_COUNT = INPUT_SLOTS_COUNT + OUTPUT_SLOTS_COUNT + 1;

    public static final int FIRST_INPUT_SLOT = 0;
    public static final int FIRST_OUTPUT_SLOT = FIRST_INPUT_SLOT + INPUT_SLOTS_COUNT;
    public static final int FIRST_CAPACITOR_SLOT = FIRST_OUTPUT_SLOT + OUTPUT_SLOTS_COUNT;

    private static final short COOK_TIME_FOR_COMPLETION = 200; // vanilla value is 200 = 10 seconds

    public TileGearSalvage() {
        super(BlockRegister.GEAR_SALVAGE);
        itemStacks = new ItemStack[TOTAL_SLOTS_COUNT];
        clear();
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

    ItemStack result = ItemStack.EMPTY;

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
        Integer firstSuitableInputSlot = null;
        Integer firstSuitableOutputSlot = null;

        // TODO

        /*
         * if (!itemStacks[FIRST_CAPACITOR_SLOT].isEmpty()) {
         *
         * Item item = itemStacks[FIRST_CAPACITOR_SLOT].getItem();
         *
         * if (item instanceof ItemCapacitor) { fuelMulti = ((ItemCapacitor)
         * item).GetFuelMultiplier(); // System.out.println("it works!"); }
         *
         * }
         */

        // finds the first input slot which is smeltable and whose result fits into an
        // output slot (stacking if possible)
        for (int inputSlot = FIRST_INPUT_SLOT; inputSlot < FIRST_INPUT_SLOT + INPUT_SLOTS_COUNT; inputSlot++) {
            if (!itemStacks[inputSlot].isEmpty()) { // isEmpty()

                result = getSmeltingResultForItem(itemStacks[inputSlot]);

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
            result = ItemStack.EMPTY;

        } else {
            int newStackSize = itemStacks[firstSuitableOutputSlot].getCount() + result.getCount();
            itemStacks[firstSuitableOutputSlot].setCount(newStackSize); // setStackSize(), getStackSize()
        }

        markDirty();
        return true;
    }

    @Override
    public boolean isItemValidInput(ItemStack stack) {
        ItemStack result = this.getSmeltingResultForItem(stack);

        return result.isEmpty() == false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return CLOC.blank("block.mmorpg.salvage_station");
    }

    @Nullable
    @Override
    public Container createMenu(int num, PlayerInventory inventory, PlayerEntity player) {
        return new ContainerGearSalvage(num, inventory, this, this.getPos());
    }
}