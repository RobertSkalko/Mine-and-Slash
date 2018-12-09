package com.robertx22.unique_items.bases;

import com.robertx22.customitems.gearitems.baubles.ItemBracelet;
import com.robertx22.database.gearitemslots.Bracelet;
import com.robertx22.unique_items.IUnique;

import baubles.api.BaubleType;
import net.minecraft.item.ItemStack;

public abstract class BaseUniqueBracelet extends ItemBracelet implements IUnique {

    public BaseUniqueBracelet() {
	IUnique.ITEMS.put(GUID(), this);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
	return BaubleType.BELT;
    }

    @Override
    public String slot() {
	return new Bracelet().GUID();
    }
}
