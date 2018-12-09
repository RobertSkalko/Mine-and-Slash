package com.robertx22.unique_items.bases;

import com.robertx22.customitems.gearitems.armor.ItemBoots;
import com.robertx22.database.gearitemslots.Boots;
import com.robertx22.database.rarities.items.UniqueItem;
import com.robertx22.unique_items.IUnique;

public abstract class BaseUniqueBoots extends ItemBoots implements IUnique {

    public BaseUniqueBoots() {
	super(new UniqueItem().Rank());
	IUnique.ITEMS.put(GUID(), this);

    }

    @Override
    public String slot() {
	return new Boots().GUID();
    }

}
