package com.robertx22.advanced_blocks.map_device;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerMap extends Container {

    // Stores the tile entity instance for later use
    private TileMap tile;

    // These store cache values, used by the server to only update the client side
    // tile entity when values have changed
    private int[] cachedFields;

    private final int HOTBAR_SLOT_COUNT = 9;
    private final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

    // slot index is the unique index for all slots in this container i.e. 0 - 35
    // for invPlayer then 36 - 49 for tileInventoryFurnace
    private final int VANILLA_FIRST_SLOT_INDEX = 0;
    private final int TIER_SLOT_INDEX = 0;
    private final int LEVEL_SLOT_INDEX = 1;
    private final int MAP_SLOT_INDEX = 2;
    private final int START_SLOT_INDEX = 3;

    public ContainerMap(InventoryPlayer invPlayer, TileMap tile) {
	this.tile = tile;

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
	final int TIER_X = 26;
	final int TIER_Y = 85;
	addSlotToContainer(new NormalSlot(tile, TIER_SLOT_INDEX, TIER_X, TIER_Y));

	final int LEVEL_X = 134;
	final int LEVEL_Y = 85;
	addSlotToContainer(new NormalSlot(tile, LEVEL_SLOT_INDEX, LEVEL_X, LEVEL_Y));

	final int MAP_X = 81;
	final int MAP_Y = 28;
	addSlotToContainer(new NormalSlot(tile, MAP_SLOT_INDEX, MAP_X, MAP_Y));

	final int START_X = 80;
	final int START_Y = 99;
	addSlotToContainer(new NormalSlot(tile, START_SLOT_INDEX, START_X, START_Y));

    }

    // Checks each tick to make sure the player is still able to access the
    // inventory and if not closes the gui
    @Override
    public boolean canInteractWith(EntityPlayer player) {
	return tile.isUsableByPlayer(player);
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

    }

    // Called when a progress bar update is received from the server. The two values
    // (id and data) are the same two
    // values given to sendWindowProperty. In this case we are using fields so we
    // just pass them to the tileEntity.
    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int data) {
	tile.setField(id, data);
    }

    // SlotSmeltableInput is a slot for input items
    public class NormalSlot extends Slot {
	public NormalSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
	    super(inventoryIn, index, xPosition, yPosition);
	}

	// if this function returns false, the player won't be able to insert the given
	// item into this slot
	@Override
	public boolean isItemValid(ItemStack stack) {
	    return true;
	}
    }

}