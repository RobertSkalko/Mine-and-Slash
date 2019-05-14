package com.robertx22.blocks.item_modify_station;

import com.robertx22.blocks.salvage_station.TileInventorySalvage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerInventoryModify extends Container {

    // Stores the tile entity instance for later use
    private TileInventoryModify tileInventory;

    // These store cache values, used by the server to only update the client side
    // tile entity when values have changed
    private int[] cachedFields;

    private final int HOTBAR_SLOT_COUNT = 9;
    private final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

    public final int INPUT_SLOTS_COUNT = 2;
    public final int OUTPUT_SLOTS_COUNT = 1;
    public final int MODIFY_SLOTS_COUNT = INPUT_SLOTS_COUNT + OUTPUT_SLOTS_COUNT;

    // slot index is the unique index for all slots in this container i.e. 0 - 35
    // for invPlayer then 36 - 49 for tileInventoryFurnace
    private final int VANILLA_FIRST_SLOT_INDEX = 0;
    private final int FIRST_INPUT_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private final int FIRST_OUTPUT_SLOT_INDEX = FIRST_INPUT_SLOT_INDEX + INPUT_SLOTS_COUNT;

    // slot number is the slot number within each component; i.e. invPlayer slots 0
    // - 35, and tileInventoryFurnace slots 0 - 14
    private final int FIRST_INPUT_SLOT_NUMBER = 0;
    private final int FIRST_OUTPUT_SLOT_NUMBER = FIRST_INPUT_SLOT_NUMBER + INPUT_SLOTS_COUNT;

    public ContainerInventoryModify(InventoryPlayer invPlayer, TileInventoryModify tileInventory) {
	this.tileInventory = tileInventory;

	final int SLOT_X_SPACING = 18;
	final int SLOT_Y_SPACING = 18;
	final int HOTBAR_XPOS = 8;
	final int HOTBAR_YPOS = 183;
	// Add the players hotbar to the gui - the [xpos, ypos] location of each item
	for (int x = 0; x < HOTBAR_SLOT_COUNT; x++) {
	    int slotNumber = x;
	    addSlotToContainer(new Slot(invPlayer, slotNumber, HOTBAR_XPOS + SLOT_X_SPACING * x, HOTBAR_YPOS));
	}

	final int PLAYER_INVENTORY_XPOS = 8;
	final int PLAYER_INVENTORY_YPOS = 125;
	// Add the rest of the players inventory to the gui
	for (int y = 0; y < PLAYER_INVENTORY_ROW_COUNT; y++) {
	    for (int x = 0; x < PLAYER_INVENTORY_COLUMN_COUNT; x++) {
		int slotNumber = HOTBAR_SLOT_COUNT + y * PLAYER_INVENTORY_COLUMN_COUNT + x;
		int xpos = PLAYER_INVENTORY_XPOS + x * SLOT_X_SPACING;
		int ypos = PLAYER_INVENTORY_YPOS + y * SLOT_Y_SPACING;
		addSlotToContainer(new Slot(invPlayer, slotNumber, xpos, ypos));
	    }
	}

	// VANILLA END

	final int INPUT_SLOTS_XPOS = 26;
	final int INPUT_SLOTS_YPOS = 24;
	// Add the tile input slots

	addSlotToContainer(new SlotSmeltableInput(tileInventory, FIRST_INPUT_SLOT_NUMBER, INPUT_SLOTS_XPOS,
		INPUT_SLOTS_YPOS + SLOT_Y_SPACING * 2));

	addSlotToContainer(new SlotSmeltableInput(tileInventory, FIRST_INPUT_SLOT_NUMBER + 1, 72,
		INPUT_SLOTS_YPOS + SLOT_Y_SPACING * 1));

	final int OUTPUT_SLOTS_XPOS = 134;
	final int OUTPUT_SLOTS_YPOS = 24;

	addSlotToContainer(new SlotOutput(tileInventory, FIRST_OUTPUT_SLOT_NUMBER, OUTPUT_SLOTS_XPOS,
		OUTPUT_SLOTS_YPOS + SLOT_Y_SPACING * 2));

    }

    // Checks each tick to make sure the player is still able to access the
    // inventory and if not closes the gui
    @Override
    public boolean canInteractWith(EntityPlayer player) {
	return tileInventory.isUsableByPlayer(player);
    }

    // shift click logic
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int sourceSlotIndex) {
	return ItemStack.EMPTY;
    }

    /* Client Synchronization */
    @Override
    public void detectAndSendChanges() {
	super.detectAndSendChanges();

	boolean allFieldsHaveChanged = false;
	boolean fieldHasChanged[] = new boolean[tileInventory.getFieldCount()];
	if (cachedFields == null) {
	    cachedFields = new int[tileInventory.getFieldCount()];
	    allFieldsHaveChanged = true;
	}
	for (int i = 0; i < cachedFields.length; ++i) {
	    if (allFieldsHaveChanged || cachedFields[i] != tileInventory.getField(i)) {
		cachedFields[i] = tileInventory.getField(i);
		fieldHasChanged[i] = true;
	    }
	}

	// go through the list of listeners (players using this container) and update
	// them if necessary
	for (IContainerListener listener : this.listeners) {
	    for (int fieldID = 0; fieldID < tileInventory.getFieldCount(); ++fieldID) {
		if (fieldHasChanged[fieldID]) {
		    // Note that although sendWindowProperty takes 2 ints on a server these are
		    // truncated to shorts
		    listener.sendWindowProperty(this, fieldID, cachedFields[fieldID]);
		}
	    }
	}
    }

    // Called when a progress bar update is received from the server. The two values
    // (id and data) are the same two
    // values given to sendWindowProperty. In this case we are using fields so we
    // just pass them to the tileEntity.
    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int data) {
	tileInventory.setField(id, data);
    }

    // SlotSmeltableInput is a slot for input items
    public class SlotSmeltableInput extends Slot {
	public SlotSmeltableInput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
	    super(inventoryIn, index, xPosition, yPosition);
	}

	// if this function returns false, the player won't be able to insert the given
	// item into this slot
	@Override
	public boolean isItemValid(ItemStack stack) {
	    return TileInventorySalvage.isItemValidForInputSlot(stack);
	}
    }

    // SlotOutput is a slot that will not accept any items
    public class SlotOutput extends Slot {
	public SlotOutput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
	    super(inventoryIn, index, xPosition, yPosition);
	}

	// if this function returns false, the player won't be able to insert the given
	// item into this slot
	@Override
	public boolean isItemValid(ItemStack stack) {
	    return TileInventorySalvage.isItemValidForOutputSlot(stack);
	}
    }
}