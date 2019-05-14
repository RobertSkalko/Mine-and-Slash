package com.robertx22.items.bags.loot_bag;

import com.robertx22.items.bags.BaseContainer;
import com.robertx22.items.bags.BaseInventory;
import com.robertx22.items.bags.BaseSlot;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class ContainerLootBag extends BaseContainer {

    public ContainerLootBag(InventoryPlayer playerInv, BaseInventory basebag) {
	super(playerInv, basebag);

    }

    public class SlotLootBag extends BaseSlot {

	public SlotLootBag(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
	    super(itemHandler, index, xPosition, yPosition);

	}

	@Override
	public boolean isItemValid(ItemStack stack) {
	    return new ItemLootBag().IsValidItem(stack);
	}

	@Override
	public BaseSlot newObject(IItemHandler inv, int index, int x, int y) {
	    return new SlotLootBag(inv, index, x, y);
	}

    }

    @Override
    public BaseSlot slot(IItemHandler inv, int index, int x, int y) {
	return new SlotLootBag(inv, index, x, y);
    }

}