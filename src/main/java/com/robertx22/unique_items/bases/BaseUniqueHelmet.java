package com.robertx22.unique_items.bases;

import com.robertx22.customitems.gearitems.armor.ItemHelmet;
import com.robertx22.database.gearitemslots.Helmet;
import com.robertx22.database.rarities.items.UniqueItem;
import com.robertx22.unique_items.IUnique;

public abstract class BaseUniqueHelmet extends ItemHelmet implements IUnique {

    public BaseUniqueHelmet() {
	super(new UniqueItem().Rank());

	IUnique.ITEMS.put(GUID(), this);
    }

    @Override
    public String slot() {
	return new Helmet().GUID();
    }

}
