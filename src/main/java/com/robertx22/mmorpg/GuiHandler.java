package com.robertx22.mmorpg;

import com.robertx22.advanced_blocks.repair_station.ContainerInventoryRepair;
import com.robertx22.advanced_blocks.repair_station.GuiInventoryRepair;
import com.robertx22.advanced_blocks.repair_station.TileInventoryRepair;
import com.robertx22.advanced_blocks.salvage_station.ContainerInventorySalvage;
import com.robertx22.advanced_blocks.salvage_station.GuiInventorySalvage;
import com.robertx22.advanced_blocks.salvage_station.TileInventorySalvage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * User: brandon3055 Date: 06/01/2015
 *
 * This class is used to get the client and server gui elements when a player
 * opens a gui. There can only be one registered IGuiHandler instance handler
 * per mod.
 */
public class GuiHandler implements IGuiHandler {
	private static final int GUIID_MBE_31 = 31;

	public static int getGuiID() {
		return GUIID_MBE_31;
	}

	// Gets the server side element for the given gui id this should return a
	// container
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID != getGuiID()) {
			System.err.println("Invalid ID: expected " + getGuiID() + ", received " + ID);
		}

		BlockPos xyz = new BlockPos(x, y, z);
		TileEntity tileEntity = world.getTileEntity(xyz);
		if (tileEntity instanceof TileInventoryRepair) {
			TileInventoryRepair tileInventory = (TileInventoryRepair) tileEntity;
			return new ContainerInventoryRepair(player.inventory, tileInventory);
		}

		if (tileEntity instanceof TileInventorySalvage) {
			TileInventorySalvage tileInventory = (TileInventorySalvage) tileEntity;
			return new ContainerInventorySalvage(player.inventory, tileInventory);
		}
		return null;
	}

	// Gets the client side element for the given gui id this should return a gui
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID != getGuiID()) {
			System.err.println("Invalid ID: expected " + getGuiID() + ", received " + ID);
		}

		BlockPos xyz = new BlockPos(x, y, z);
		TileEntity tileEntity = world.getTileEntity(xyz);
		if (tileEntity instanceof TileInventoryRepair) {
			TileInventoryRepair tileInventory = (TileInventoryRepair) tileEntity;
			return new GuiInventoryRepair(player.inventory, tileInventory);
		}
		if (tileEntity instanceof TileInventorySalvage) {
			TileInventorySalvage tileInventory = (TileInventorySalvage) tileEntity;
			return new GuiInventorySalvage(player.inventory, tileInventory);
		}

		return null;
	}

}