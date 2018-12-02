package com.robertx22.mmorpg.gui;

import com.robertx22.advanced_blocks.gear_factory_station.ContainerGearFactory;
import com.robertx22.advanced_blocks.gear_factory_station.GuiGearFactory;
import com.robertx22.advanced_blocks.gear_factory_station.TileGearFactory;
import com.robertx22.advanced_blocks.item_modify_station.ContainerInventoryModify;
import com.robertx22.advanced_blocks.item_modify_station.GuiInventoryModify;
import com.robertx22.advanced_blocks.item_modify_station.TileInventoryModify;
import com.robertx22.advanced_blocks.map_device.ContainerMap;
import com.robertx22.advanced_blocks.map_device.GuiMap;
import com.robertx22.advanced_blocks.map_device.TileMap;
import com.robertx22.advanced_blocks.repair_station.ContainerInventoryRepair;
import com.robertx22.advanced_blocks.repair_station.GuiInventoryRepair;
import com.robertx22.advanced_blocks.repair_station.TileInventoryRepair;
import com.robertx22.advanced_blocks.salvage_station.ContainerInventorySalvage;
import com.robertx22.advanced_blocks.salvage_station.GuiInventorySalvage;
import com.robertx22.advanced_blocks.salvage_station.TileInventorySalvage;
import com.robertx22.customitems.bags.currency_bag.ContainerCurrencyBag;
import com.robertx22.customitems.bags.currency_bag.GuiCurrencyBag;
import com.robertx22.customitems.bags.currency_bag.InventoryCurrencyBag;
import com.robertx22.customitems.bags.currency_bag.ItemCurrencyBag;
import com.robertx22.customitems.bags.loot_bag.ContainerLootBag;
import com.robertx22.customitems.bags.loot_bag.GuiLootBag;
import com.robertx22.customitems.bags.loot_bag.InventoryLootBag;
import com.robertx22.customitems.bags.loot_bag.ItemLootBag;
import com.robertx22.customitems.bags.map_bag.ContainerMapBag;
import com.robertx22.customitems.bags.map_bag.GuiMapBag;
import com.robertx22.customitems.bags.map_bag.InventoryMapBag;
import com.robertx22.customitems.bags.map_bag.ItemMapBag;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
    private static final int GUI_MMORPG = 750;

    public static int getGuiID() {
	return GUI_MMORPG;
    }

    // Gets the server side element for the given gui id this should return a
    // container
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

	ItemStack stack = player.getHeldItemMainhand();

	BlockPos xyz = new BlockPos(x, y, z);
	TileEntity tileEntity = world.getTileEntity(xyz);
	if (tileEntity instanceof TileInventoryRepair) {
	    TileInventoryRepair tileInventory = (TileInventoryRepair) tileEntity;
	    return new ContainerInventoryRepair(player.inventory, tileInventory);
	}

	if (tileEntity instanceof TileInventorySalvage) {
	    TileInventorySalvage tile = (TileInventorySalvage) tileEntity;
	    return new ContainerInventorySalvage(player.inventory, tile);
	}

	if (tileEntity instanceof TileInventoryModify) {
	    TileInventoryModify tile = (TileInventoryModify) tileEntity;
	    return new ContainerInventoryModify(player.inventory, tile);
	}

	if (tileEntity instanceof TileGearFactory) {
	    TileGearFactory tile = (TileGearFactory) tileEntity;
	    return new ContainerGearFactory(player.inventory, tile);
	}
	if (tileEntity instanceof TileMap) {
	    TileMap tile = (TileMap) tileEntity;
	    return new ContainerMap(player.inventory, tile);
	}

	if (stack != null && !stack.isEmpty()) {
	    if (ID == ItemCurrencyBag.GUI_NUMBER) {
		if (stack.getItem() instanceof ItemCurrencyBag)
		    return new ContainerCurrencyBag(player.inventory, new InventoryCurrencyBag(stack));
	    }
	    if (ID == ItemLootBag.GUI_NUMBER) {
		if (stack.getItem() instanceof ItemLootBag)
		    return new ContainerLootBag(player.inventory, new InventoryLootBag(stack));
	    }
	    if (ID == ItemMapBag.GUI_NUMBER) {
		if (stack.getItem() instanceof ItemMapBag)
		    return new ContainerMapBag(player.inventory, new InventoryMapBag(stack));
	    }

	}
	return null;
    }

    // Gets the client side element for the given gui id this should return a gui
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

	ItemStack stack = player.getHeldItemMainhand();

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
	if (tileEntity instanceof TileInventoryModify) {
	    TileInventoryModify tileInventory = (TileInventoryModify) tileEntity;
	    return new GuiInventoryModify(player.inventory, tileInventory);
	}
	if (tileEntity instanceof TileGearFactory) {
	    TileGearFactory tileInventory = (TileGearFactory) tileEntity;
	    return new GuiGearFactory(player.inventory, tileInventory);
	}
	if (tileEntity instanceof TileMap) {
	    TileMap tileInventory = (TileMap) tileEntity;
	    return new GuiMap(player.inventory, tileInventory);
	}

	if (stack != null && !stack.isEmpty()) {
	    if (ID == ItemCurrencyBag.GUI_NUMBER) {
		if (stack.getItem() instanceof ItemCurrencyBag) {
		    return new GuiCurrencyBag(player.inventory, new InventoryCurrencyBag(stack));
		}
	    }
	    if (ID == ItemLootBag.GUI_NUMBER) {
		if (stack.getItem() instanceof ItemLootBag) {
		    return new GuiLootBag(player.inventory, new InventoryLootBag(stack));
		}
	    }
	    if (ID == ItemMapBag.GUI_NUMBER) {
		if (stack.getItem() instanceof ItemMapBag) {
		    return new GuiMapBag(player.inventory, new InventoryMapBag(stack));
		}
	    }
	}

	return null;
    }

}