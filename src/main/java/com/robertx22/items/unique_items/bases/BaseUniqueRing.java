package com.robertx22.items.unique_items.bases;

import com.robertx22.database.gearitemslots.Ring;
import com.robertx22.items.gearitems.baubles.ItemRing;
import com.robertx22.items.unique_items.IUnique;

import baubles.api.BaubleType;

public abstract class BaseUniqueRing extends ItemRing implements IUnique {
	public BaseUniqueRing(BaubleType type) {
		super(type);
		IUnique.ITEMS.put(GUID(), this);
	}

	@Override
	public String slot() {
		return new Ring().GUID();
	}
}