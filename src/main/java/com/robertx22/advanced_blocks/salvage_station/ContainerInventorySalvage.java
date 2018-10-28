package com.robertx22.advanced_blocks.salvage_station;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerInventorySalvage extends Container {

	// Stores the tile entity instance for later use
	private TileInventorySalvage tileInventorySalvage;

	// These store cache values, used by the server to only update the client side
	// tile entity when values have changed
	private int[] cachedFields;

	private final int HOTBAR_SLOT_COUNT = 9;
	private final int PLAYER_INVENTORY_ROW_COUNT = 3;
	private final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
	private final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
	private final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

	public final int INPUT_SLOTS_COUNT = 5;
	public final int OUTPUT_SLOTS_COUNT = 5;
	public final int SALVAGE_SLOTS_COUNT = INPUT_SLOTS_COUNT + OUTPUT_SLOTS_COUNT + 1;

	// slot index is the unique index for all slots in this container i.e. 0 - 35
	// for invPlayer then 36 - 49 for tileInventoryFurnace
	private final int VANILLA_FIRST_SLOT_INDEX = 0;
	private final int FIRST_INPUT_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
	private final int FIRST_OUTPUT_SLOT_INDEX = FIRST_INPUT_SLOT_INDEX + INPUT_SLOTS_COUNT;
	private final int FIRST_CAPACITOR_SLOT_INDEX = FIRST_OUTPUT_SLOT_INDEX + OUTPUT_SLOTS_COUNT;

	// slot number is the slot number within each component; i.e. invPlayer slots 0
	// - 35, and tileInventoryFurnace slots 0 - 14
	private final int FIRST_INPUT_SLOT_NUMBER = 0;
	private final int FIRST_OUTPUT_SLOT_NUMBER = FIRST_INPUT_SLOT_NUMBER + INPUT_SLOTS_COUNT;
	private final int FIRST_CAPACITOR_SLOT_NUMBER = FIRST_OUTPUT_SLOT_NUMBER + OUTPUT_SLOTS_COUNT;

	public ContainerInventorySalvage(InventoryPlayer invPlayer, TileInventorySalvage tileInventorySalvage) {
		this.tileInventorySalvage = tileInventorySalvage;

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

		final int INPUT_SLOTS_XPOS = 26;
		final int INPUT_SLOTS_YPOS = 24;
		// Add the tile input slots
		for (int y = 0; y < INPUT_SLOTS_COUNT; y++) {
			int slotNumber = y + FIRST_INPUT_SLOT_NUMBER;
			addSlotToContainer(new SlotSmeltableInput(tileInventorySalvage, slotNumber, INPUT_SLOTS_XPOS,
					INPUT_SLOTS_YPOS + SLOT_Y_SPACING * y));
		}

		final int OUTPUT_SLOTS_XPOS = 134;
		final int OUTPUT_SLOTS_YPOS = 24;
		// Add the tile output slots
		for (int y = 0; y < OUTPUT_SLOTS_COUNT; y++) {
			int slotNumber = y + FIRST_OUTPUT_SLOT_NUMBER;
			addSlotToContainer(new SlotOutput(tileInventorySalvage, slotNumber, OUTPUT_SLOTS_XPOS,
					OUTPUT_SLOTS_YPOS + SLOT_Y_SPACING * y));
		}

		final int CAPACITOR_SLOTS_XPOS = 80; // 53; // TODO
		final int CAPACITOR_SLOTS_YPOS = 24;
		// Add the tile capacitor slot
		for (int x = 0; x < 1; x++) {
			int slotNumber = x + FIRST_CAPACITOR_SLOT_NUMBER;
			addSlotToContainer(new Slot(tileInventorySalvage, slotNumber, CAPACITOR_SLOTS_XPOS + SLOT_X_SPACING * x,
					CAPACITOR_SLOTS_YPOS));
		}

	}

	// Checks each tick to make sure the player is still able to access the
	// inventory and if not closes the gui
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileInventorySalvage.isUsableByPlayer(player);
	}

	private boolean IsCustomContainer(int index) {

		return index > VANILLA_FIRST_SLOT_INDEX + this.VANILLA_SLOT_COUNT;
	}

	// shift click logic
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int sourceSlotIndex) {
		Slot sourceSlot = (Slot) inventorySlots.get(sourceSlotIndex);
		if (sourceSlot == null || !sourceSlot.getHasStack())
			return ItemStack.EMPTY; // EMPTY_ITEM
		ItemStack sourceStack = sourceSlot.getStack();
		ItemStack copyOfSourceStack = sourceStack.copy();

		// Check if the slot clicked is one of the vanilla container slots
		if (sourceSlotIndex >= VANILLA_FIRST_SLOT_INDEX
				&& sourceSlotIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
			// if (!TileInventorySalvage.getSmeltingResultForItem(sourceStack).isEmpty()) {
			// // isEmptyItem
			if (!mergeItemStack(sourceStack, FIRST_INPUT_SLOT_INDEX, FIRST_INPUT_SLOT_INDEX + INPUT_SLOTS_COUNT,
					false)) {
				return ItemStack.EMPTY; // EMPTY_ITEM;
				// }
			} else {
				return ItemStack.EMPTY; // this is the thing that gave me problems.. without this it crashes without an
			} // error message

		} else if (sourceSlotIndex >= FIRST_INPUT_SLOT_INDEX
				&& sourceSlotIndex < FIRST_INPUT_SLOT_INDEX + SALVAGE_SLOTS_COUNT) {
			if (!mergeItemStack(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT,
					false)) {
				return ItemStack.EMPTY; // EMPTY_ITEM;
			}
		} else {
			System.err.print("Invalid slotIndex:" + sourceSlotIndex);
			return ItemStack.EMPTY; // EMPTY_ITEM;
		}

		// If stack size == 0 (the entire stack was moved) set slot contents to null
		if (sourceStack.getCount() == 0) { // getStackSize()
			sourceSlot.putStack(ItemStack.EMPTY); // Empty Item
		} else {
			sourceSlot.onSlotChanged();
		}

		sourceSlot.onTake(player, sourceStack); // onPickupFromSlot()
		return copyOfSourceStack;
	}

	/* Client Synchronization */
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		boolean allFieldsHaveChanged = false;
		boolean fieldHasChanged[] = new boolean[tileInventorySalvage.getFieldCount()];
		if (cachedFields == null) {
			cachedFields = new int[tileInventorySalvage.getFieldCount()];
			allFieldsHaveChanged = true;
		}
		for (int i = 0; i < cachedFields.length; ++i) {
			if (allFieldsHaveChanged || cachedFields[i] != tileInventorySalvage.getField(i)) {
				cachedFields[i] = tileInventorySalvage.getField(i);
				fieldHasChanged[i] = true;
			}
		}

		// go through the list of listeners (players using this container) and update
		// them if necessary
		for (IContainerListener listener : this.listeners) {
			for (int fieldID = 0; fieldID < tileInventorySalvage.getFieldCount(); ++fieldID) {
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
		tileInventorySalvage.setField(id, data);
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