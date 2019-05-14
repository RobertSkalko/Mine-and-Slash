package com.robertx22.items.unique_items.bases;

import com.robertx22.database.gearitemslots.Chest;
import com.robertx22.database.rarities.items.UniqueItem;
import com.robertx22.items.gearitems.armor.ItemChest;
import com.robertx22.items.unique_items.IUnique;

public abstract class BaseUniqueChest extends ItemChest implements IUnique {

    public BaseUniqueChest() {
	super(new UniqueItem().Rank());
	IUnique.ITEMS.put(GUID(), this);

    }

    @Override
    public String slot() {
	return new Chest().GUID();
    }

}
