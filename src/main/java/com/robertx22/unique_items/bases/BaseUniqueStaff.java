package com.robertx22.unique_items.bases;

import com.robertx22.customitems.gearitems.weapons.ItemStaff;
import com.robertx22.database.gearitemslots.Staff;
import com.robertx22.unique_items.IUnique;

public abstract class BaseUniqueStaff extends ItemStaff implements IUnique {

    public BaseUniqueStaff() {
	IUnique.ITEMS.put(GUID(), this);
    }

    @Override
    public String slot() {
	return new Staff().GUID();
    }
}
