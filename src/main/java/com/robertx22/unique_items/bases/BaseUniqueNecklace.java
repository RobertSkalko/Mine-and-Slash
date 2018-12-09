package com.robertx22.unique_items.bases;

import com.robertx22.customitems.gearitems.baubles.ItemNecklace;
import com.robertx22.database.gearitemslots.Necklace;
import com.robertx22.unique_items.IUnique;

import baubles.api.BaubleType;
import net.minecraft.item.ItemStack;

public abstract class BaseUniqueNecklace extends ItemNecklace implements IUnique {
    public BaseUniqueNecklace() {
	IUnique.ITEMS.put(GUID(), this);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
	return BaubleType.AMULET;
    }

    @Override
    public String slot() {
	return new Necklace().GUID();
    }
}
