package com.robertx22.customitems.bags.currency_bag;

import com.robertx22.customitems.bags.BaseContainer;
import com.robertx22.customitems.bags.BaseInventory;
import com.robertx22.customitems.bags.BaseSlot;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class ContainerCurrencyBag extends BaseContainer {

    public ContainerCurrencyBag(InventoryPlayer playerInv, BaseInventory basebag) {
	super(playerInv, basebag);

    }

    public class SlotCurrency extends BaseSlot {

	public SlotCurrency(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
	    super(itemHandler, index, xPosition, yPosition);

	}

	@Override
	public boolean isItemValid(ItemStack stack) {
	    return new ItemCurrencyBag().IsValidItem(stack);
	}

	@Override
	public BaseSlot newObject(IItemHandler inv, int index, int x, int y) {
	    return new SlotCurrency(inv, index, x, y);
	}

    }

    @Override
    public BaseSlot slot(IItemHandler inv, int index, int x, int y) {
	return new SlotCurrency(inv, index, x, y);
    }

}