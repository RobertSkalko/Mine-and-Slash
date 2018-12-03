package com.robertx22.advanced_blocks.map_device;

import java.util.Arrays;

import javax.annotation.Nullable;

import com.robertx22.advanced_blocks.BaseTile;
import com.robertx22.customitems.misc.ItemMap;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.uncommon.datasaving.Map;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class TileMap extends BaseTile {

    public ItemStack StartSlot() {
	return itemStacks[3];
    }

    public ItemStack TierSlot() {
	return itemStacks[0];
    }

    public ItemStack LevelSlot() {
	return itemStacks[1];
    }

    public ItemStack MapSlot() {
	return itemStacks[2];
    }

    public TileMap() {
	itemStacks = new ItemStack[4];
	clear();
    }

    int ticks = 0;

    @Override
    public void update() {
	if (!this.world.isRemote) {

	    ticks++;
	    if (ticks > 20) {
		ticks = 0;

		doLogic();

	    }
	}
    }

    private void doLogic() {

	ItemStack start = this.StartSlot();

	MapItemData map = Map.Load(this.MapSlot());
	MapItemData level = Map.Load(this.LevelSlot());
	MapItemData tier = Map.Load(this.TierSlot());

	if (map != null) {

	    if (start != null && start.getItem().equals(Items.WHEAT_SEEDS)) {

		// start map
		this.MapSlot().shrink(1);
		this.StartSlot().shrink(1);

		int id = map.createDimension(world, pos);

		BlockPos pos = this.pos.north(4);
		ItemMap.createMap(id, pos, world, map);

		BlockPos pos1 = this.pos.south(4);
		ItemMap.createMap(id, pos1, world, map);

		BlockPos pos2 = this.pos.east(4);
		ItemMap.createMap(id, pos2, world, map);

		BlockPos pos3 = this.pos.west(4);
		ItemMap.createMap(id, pos3, world, map);

	    }

	    else if (level != null) {

		if (map.increaseLevel(level.rarity + 1)) {
		    this.LevelSlot().shrink(1);
		    Map.Save(this.MapSlot(), map);
		}

	    } else if (tier != null) {

		if (map.increaseTier(tier.rarity + 1)) {
		    this.TierSlot().shrink(1);
		    Map.Save(this.MapSlot(), map);
		}
	    }
	}

	markDirty();

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound parentNBTTagCompound) {
	super.writeToNBT(parentNBTTagCompound); // The super call is required to save and load the tiles location

	NBTTagList dataForAllSlots = new NBTTagList();
	for (int i = 0; i < this.itemStacks.length; ++i) {
	    if (!this.itemStacks[i].isEmpty()) { // isEmpty()
		NBTTagCompound dataForThisSlot = new NBTTagCompound();
		dataForThisSlot.setByte("Slot", (byte) i);
		this.itemStacks[i].writeToNBT(dataForThisSlot);
		dataForAllSlots.appendTag(dataForThisSlot);
	    }
	}
	// the array of hashmaps is then inserted into the parent hashmap for the
	// container
	parentNBTTagCompound.setTag("Items", dataForAllSlots);

	// Save everything else

	return parentNBTTagCompound;
    }

    // This is where you load the data that you saved in writeToNBT
    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
	super.readFromNBT(nbtTagCompound); // The super call is required to save and load the tiles location
	final byte NBT_TYPE_COMPOUND = 10; // See NBTBase.createNewByType() for a listing
	NBTTagList dataForAllSlots = nbtTagCompound.getTagList("Items", NBT_TYPE_COMPOUND);

	Arrays.fill(itemStacks, ItemStack.EMPTY); // set all slots to empty EMPTY_ITEM
	for (int i = 0; i < dataForAllSlots.tagCount(); ++i) {
	    NBTTagCompound dataForOneSlot = dataForAllSlots.getCompoundTagAt(i);
	    byte slotNumber = dataForOneSlot.getByte("Slot");
	    if (slotNumber >= 0 && slotNumber < this.itemStacks.length) {
		this.itemStacks[slotNumber] = new ItemStack(dataForOneSlot);
	    }
	}

    }

//	// When the world loads from disk, the server needs to send the TileEntity information to the client
//	//  it uses getUpdatePacket(), getUpdateTag(), onDataPacket(), and handleUpdateTag() to do this
    @Override
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
	NBTTagCompound updateTagDescribingTileEntityState = getUpdateTag();
	final int METADATA = 0;
	return new SPacketUpdateTileEntity(this.pos, METADATA, updateTagDescribingTileEntityState);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
	NBTTagCompound updateTagDescribingTileEntityState = pkt.getNbtCompound();
	handleUpdateTag(updateTagDescribingTileEntityState);
    }

    /*
     * Creates a tag containing the TileEntity information, used by vanilla to
     * transmit from server to client Warning - although our getUpdatePacket() uses
     * this method, vanilla also calls it directly, so don't remove it.
     */
    @Override
    public NBTTagCompound getUpdateTag() {
	NBTTagCompound nbtTagCompound = new NBTTagCompound();
	writeToNBT(nbtTagCompound);
	return nbtTagCompound;
    }

    /*
     * Populates this TileEntity with information from the tag, used by vanilla to
     * transmit from server to client Warning - although our onDataPacket() uses
     * this method, vanilla also calls it directly, so don't remove it.
     */
    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
	this.readFromNBT(tag);
    }
    // ------------------------

    // will add a key for this container to the lang file so we can name it in the
    // GUI
    @Override
    public String getName() {
	return "";
    }

    @Override
    public boolean hasCustomName() {
	return false;
    }

    // standard code to look up what the human-readable name is
    @Nullable
    @Override
    public ITextComponent getDisplayName() {
	return this.hasCustomName() ? new TextComponentString(this.getName())
		: new TextComponentTranslation(this.getName());
    }

    @Override
    public int getField(int id) {
	return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
	return 0;
    }

}