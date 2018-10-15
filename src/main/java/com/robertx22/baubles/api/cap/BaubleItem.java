package com.robertx22.baubles.api.cap;

import com.robertx22.baubles.api.BaubleType;
import com.robertx22.baubles.api.IBauble;

import net.minecraft.item.ItemStack;

public class BaubleItem implements IBauble
{
	private BaubleType baubleType;

	public BaubleItem(BaubleType type) {
		baubleType = type;
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return baubleType;
	}
}
