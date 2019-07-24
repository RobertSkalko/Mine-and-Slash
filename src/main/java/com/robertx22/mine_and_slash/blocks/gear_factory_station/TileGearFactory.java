package com.robertx22.mine_and_slash.blocks.gear_factory_station;

import com.robertx22.mine_and_slash.blocks.bases.BaseTile;
import com.robertx22.mine_and_slash.blocks.slots.FuelSlot;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.loot.blueprints.RuneBlueprint;
import com.robertx22.mine_and_slash.loot.blueprints.SpellBlueprint;
import com.robertx22.mine_and_slash.loot.gens.GearLootGen;
import com.robertx22.mine_and_slash.loot.gens.RuneLootGen;
import com.robertx22.mine_and_slash.loot.gens.SpellLootGen;
import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.RuneItemData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.SpellItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.datasaving.Rune;
import com.robertx22.mine_and_slash.uncommon.datasaving.Spell;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;

public class TileGearFactory extends BaseTile {

    @Override
    public boolean isAutomatable() {

        return false;
    }

    @Override
    public boolean isItemValidInput(ItemStack stack) {
        return GetFuelGain(stack) > 0;
    }

    private static final int pointsNeeded = 2500;
    private static final int maxFuel = 25000;

    public static int GetFuelGain(ItemStack stack) {
        Item item = stack.getItem();

        ItemOre.ItemOres.get(0);

        return FuelSlot.FUEL_VALUES.getOrDefault(item, 0);

    }

    public ItemStack getSmeltingResultForItem(ItemStack stack) {

        ItemStack result = ItemStack.EMPTY;

        if (fuel > pointsNeeded) {

            result = getGearResult(stack);

            if (result.isEmpty()) {
                result = getSpellResult(stack);
            }
            if (result.isEmpty()) {
                result = getRuneResult(stack);
            }

        }

        return result;

    }

    private ItemStack getGearResult(ItemStack stack) {
        GearItemData data = Gear.Load(stack);
        if (data != null) {
            GearBlueprint print = new GearBlueprint(data.level);
            print.LevelRange = false;
            print.maxRarity = getMaxRarity(data.Rarity);
            if (RandomUtils.roll(50)) {
                print.SetSpecificType(data.gearTypeName);
            }
            return GearLootGen.CreateStack(print);
        }

        return ItemStack.EMPTY;
    }

    private ItemStack getSpellResult(ItemStack stack) {

        SpellItemData spell = Spell.Load(stack);
        if (spell != null) {
            SpellBlueprint print = new SpellBlueprint(spell.level);
            print.LevelRange = false;
            print.maxRarity = getMaxRarity(spell.rarity);
            if (RandomUtils.roll(50)) {
                print.SetSpecificType(spell.spellGUID);
            }
            return SpellLootGen.Create(print);
        }
        return ItemStack.EMPTY;
    }

    private ItemStack getRuneResult(ItemStack stack) {
        RuneItemData rune = Rune.Load(stack);
        if (rune != null) {
            RuneBlueprint print = new RuneBlueprint(rune.level);
            print.LevelRange = false;
            print.maxRarity = getMaxRarity(rune.rarity);
            if (RandomUtils.roll(50)) {
                print.SetSpecificType(rune.name);
            }
            return RuneLootGen.Create(print);
        }
        return ItemStack.EMPTY;
    }

    private int getMaxRarity(int rar) {
        return rar + 3;
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

    public TileGearFactory() {
        super(BlockRegister.GEAR_FACTORY);
        itemStacks = new ItemStack[TOTAL_SLOTS_COUNT];
        clear();
    }

    /**
     * Returns the amount of FuelRemaining remaining on the currently burning item in the
     * given FuelRemaining slot.
     *
     * @return fraction remaining, between 0 - 1
     * @fuelSlot the number of the FuelRemaining slot (0..3)
     */
    public double fractionOfFuelRemaining(int fuelSlot) {
        if (this.fuel <= 0)
            return 0;
        double fraction = fuel / (double) maxFuel;
        return MathHelper.clamp(fraction, 0.0, 1.0);
    }

    /**
     * return the remaining burn time of the FuelRemaining in the given slot
     *
     * @param fuelSlot the number of the FuelRemaining slot (0..3)
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
        // Iterate over all the FuelRemaining slots
        for (int i = 0; i < FUEL_SLOTS_COUNT; i++) {
            int fuelSlotNumber = i + FIRST_FUEL_SLOT;

            if (this.fuel < this.maxFuel) {
                if (!itemStacks[fuelSlotNumber].isEmpty()) { // isEmpty()
                    // If the stack in this slot is not null and is FuelRemaining, set burnTimeRemaining &
                    // burnTimeInitialValue to the
                    // item's burn time and decrease the stack size

                    int fuel = GetFuelGain(itemStacks[fuelSlotNumber]);

                    if (fuel > 0) {
                        this.fuel += fuel;

                    } else {
                        return 0;
                    }

                    itemStacks[fuelSlotNumber].shrink(1); // decreaseStackSize()
                    ++burningCount;
                    inventoryChanged = true;
                    // If the stack size now equals 0 set the slot contents to the items container
                    // item. This is for FuelRemaining
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
        Integer firstSuitableInputSlot = null;
        Integer firstSuitableOutputSlot = null;
        ItemStack result = ItemStack.EMPTY; // EMPTY_ITEM

        // finds the first input slot which is smeltable and whose result fits into an
        // output slot (stacking if possible)
        for (int inputSlot = FIRST_INPUT_SLOT; inputSlot < FIRST_INPUT_SLOT + INPUT_SLOTS_COUNT; inputSlot++) {
            if (!itemStacks[inputSlot].isEmpty()) { // isEmpty()

                if (pointsNeeded < this.fuel) {
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

                        if (outputStack.getItem() == result.getItem() && ItemStack.areItemStackTagsEqual(outputStack, result)) {
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
        //itemStacks[this.FIRST_CAPACITOR_SLOT] = ItemStack.EMPTY; WTF?
        if (itemStacks[firstSuitableInputSlot].getCount() <= 0) {
            itemStacks[firstSuitableInputSlot] = ItemStack.EMPTY; // getStackSize(), EmptyItem
        }
        if (itemStacks[firstSuitableOutputSlot].isEmpty()) { // isEmpty()
            itemStacks[firstSuitableOutputSlot] = result.copy(); // Use deep .copy() to avoid altering the recipe
        } else {
            int newStackSize = itemStacks[firstSuitableOutputSlot].getCount() + result.getCount();
            itemStacks[firstSuitableOutputSlot].setCount(newStackSize); // setStackSize(), getStackSize()
        }

        fuel -= pointsNeeded;

        markDirty();
        return true;

    }

    @Override
    public ITextComponent getDisplayName() {
        return CLOC.blank("block.mmorpg.gear_factory_station");
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory,
                                PlayerEntity playerEntity) {

        return new ContainerGearFactory(i, playerInventory, this, this.pos);
    }
}