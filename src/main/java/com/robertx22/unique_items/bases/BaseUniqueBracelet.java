package com.robertx22.unique_items.bases;

import com.robertx22.database.gearitemslots.Bracelet;

import baubles.api.BaubleType;
import net.minecraft.item.ItemStack;

public abstract class BaseUniqueBracelet extends BaseUniqueBaublesItem {

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.BELT;
	}

	@Override
	public String slot() {
		return new Bracelet().Name();
	}
}
