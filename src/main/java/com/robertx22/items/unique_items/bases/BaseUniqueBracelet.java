package com.robertx22.items.unique_items.bases;

import com.robertx22.database.gearitemslots.Bracelet;
import com.robertx22.items.gearitems.baubles.ItemBracelet;
import com.robertx22.items.unique_items.IUnique;

import baubles.api.BaubleType;

public abstract class BaseUniqueBracelet extends ItemBracelet implements IUnique {

	public BaseUniqueBracelet(BaubleType type) {
		super(type);
		IUnique.ITEMS.put(GUID(), this);
	}

	@Override
	public String slot() {
		return new Bracelet().GUID();
	}
}
