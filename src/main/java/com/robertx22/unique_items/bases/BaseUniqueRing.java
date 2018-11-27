package com.robertx22.unique_items.bases;

import com.robertx22.database.gearitemslots.Ring;

import baubles.api.BaubleType;
import net.minecraft.item.ItemStack;

public abstract class BaseUniqueRing extends BaseUniqueBaublesItem {

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.RING;
	}

	@Override
	public String slot() {
		return new Ring().Name();
	}
}
