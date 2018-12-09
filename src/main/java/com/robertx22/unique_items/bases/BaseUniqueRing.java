package com.robertx22.unique_items.bases;

import com.robertx22.customitems.gearitems.baubles.ItemRing;
import com.robertx22.database.gearitemslots.Ring;
import com.robertx22.unique_items.IUnique;

import baubles.api.BaubleType;
import net.minecraft.item.ItemStack;

public abstract class BaseUniqueRing extends ItemRing implements IUnique {
    public BaseUniqueRing() {
	IUnique.ITEMS.put(GUID(), this);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
	return BaubleType.RING;
    }

    @Override
    public String slot() {
	return new Ring().GUID();
    }
}
