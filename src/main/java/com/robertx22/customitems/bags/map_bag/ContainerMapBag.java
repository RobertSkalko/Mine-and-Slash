package com.robertx22.customitems.bags.map_bag;

import com.robertx22.customitems.bags.BaseContainer;
import com.robertx22.customitems.bags.BaseInventory;
import com.robertx22.customitems.bags.BaseSlot;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class ContainerMapBag extends BaseContainer {

    public ContainerMapBag(InventoryPlayer playerInv, BaseInventory basebag) {
	super(playerInv, basebag);

    }

    public class SlotMapBag extends BaseSlot {

	public SlotMapBag(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
	    super(itemHandler, index, xPosition, yPosition);

	}

	@Override
	public boolean isItemValid(ItemStack stack) {
	    return new ItemMapBag().IsValidItem(stack);
	}

	@Override
	public BaseSlot newObject(IItemHandler inv, int index, int x, int y) {
	    return new SlotMapBag(inv, index, x, y);
	}

    }

    @Override
    public BaseSlot slot(IItemHandler inv, int index, int x, int y) {
	return new SlotMapBag(inv, index, x, y);
    }

}