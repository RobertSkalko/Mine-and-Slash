package com.robertx22.unique_items.bases;

import com.robertx22.database.gearitemslots.Necklace;

import baubles.api.BaubleType;
import net.minecraft.item.ItemStack;

public abstract class BaseUniqueNecklace extends BaseUniqueBaublesItem {

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.AMULET;
	}

	@Override
	public String slot() {
		return new Necklace().Name();
	}
}
