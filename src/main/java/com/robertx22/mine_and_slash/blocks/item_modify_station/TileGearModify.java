package com.robertx22.mine_and_slash.blocks.item_modify_station;

import com.robertx22.mine_and_slash.blocks.bases.BaseTile;
import com.robertx22.mine_and_slash.items.currency.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;

public class TileGearModify extends BaseTile {

    @Override
    public boolean isAutomatable() {
        return false;
    }

    @Override
    public boolean isItemValidInput(ItemStack stack) {
        return true;
    }

    public ItemStack getSmeltingResultForItem(ItemStack stack) {

        ItemStack gearStack = this.GearSlot();
        ItemStack craftStack = this.CraftItemSlot();

        if (gearStack == null || gearStack.isEmpty() || craftStack == null || craftStack.isEmpty()) {
            return ItemStack.EMPTY;
        }

        if (craftStack.getItem() instanceof ICurrencyItemEffect) {

            ICurrencyItemEffect effect = (ICurrencyItemEffect) craftStack.getItem();

            if (effect.canItemBeModified(gearStack, craftStack)) {
                ItemStack copy = gearStack.copy();
                copy = effect.ModifyItem(copy, craftStack);
                return copy;
            } else {
                return ItemStack.EMPTY;
            }

        }

        return ItemStack.EMPTY;
    }

    public ItemStack GearSlot() {
        return itemStacks[FIRST_INPUT_SLOT];
    }

    public ItemStack CraftItemSlot() {
        return itemStacks[FIRST_INPUT_SLOT + 1];
    }

    public ItemStack OutputSot() {
        return itemStacks[FIRST_INPUT_SLOT + 2];
    }

    public void setOutputSot(ItemStack stack) {
        itemStacks[FIRST_INPUT_SLOT + 2] = stack;
    }

    // IMPORTANT STUFF ABOVE

    // Create and initialize the itemStacks variable that will store store the
    // itemStacks
    public static final int INPUT_SLOTS_COUNT = 2;
    public static final int OUTPUT_SLOTS_COUNT = 1;
    public static final int TOTAL_SLOTS_COUNT = INPUT_SLOTS_COUNT + OUTPUT_SLOTS_COUNT;

    public static final int FIRST_INPUT_SLOT = 0;
    public static final int FIRST_OUTPUT_SLOT = FIRST_INPUT_SLOT + INPUT_SLOTS_COUNT;

    private static final short COOK_TIME_FOR_COMPLETION = 200; // vanilla value is 200 = 10 seconds

    public TileGearModify() {
        super(BlockRegister.GEAR_MODIFY);
        itemStacks = new ItemStack[TOTAL_SLOTS_COUNT];
        clear();

    }

    @Override
    public int ticksRequired() {
        return COOK_TIME_FOR_COMPLETION;
    }

    @Override
    public void finishCooking() {
        this.modifyItem();
    }

    @Override
    public boolean isCooking() {
        return canModifyItem();
    }

    @Override
    public int tickRate() {
        return 10;
    }

    @Override
    public void doActionEveryTime() {

    }

    public double fractionOfCookTimeComplete() {
        double fraction = cookTime / (double) COOK_TIME_FOR_COMPLETION;
        return MathHelper.clamp(fraction, 0.0, 1.0);
    }

    private ItemStack getResult() {

        return getSmeltingResultForItem(this.GearSlot());

    }

    private boolean canModifyItem() {

        ItemStack gear = this.GearSlot();

        if (gear.isEmpty()) {
            return false;
        }

        if (getSmeltingResultForItem(gear).isEmpty()) {
            return false;
        }

        if (OutputSot().isEmpty() == false) {
            return false;
        }

        return true;

    }

    private boolean modifyItem() {

        if (this.canModifyItem()) {

            ItemStack result = this.getResult();

            this.GearSlot().shrink(1);
            this.setOutputSot(result.copy());
            result = ItemStack.EMPTY;
            this.CraftItemSlot().shrink(1);

            markDirty();
            return true;
        } else {
            return false;
        }
    }

    @Nullable
    @Override
    public Container createMenu(int num, PlayerInventory inventory, PlayerEntity player) {
        return new ContainerGearModify(num, inventory, this, this.getPos());
    }

    @Override
    public ITextComponent getDisplayName() {
        return CLOC.blank("block.mmorpg.modify_station");
    }
}