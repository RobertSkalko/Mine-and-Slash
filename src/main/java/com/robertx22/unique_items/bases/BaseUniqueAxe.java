package com.robertx22.unique_items.bases;

import com.robertx22.customitems.gearitems.weapons.ItemAxe;
import com.robertx22.database.gearitemslots.Axe;
import com.robertx22.unique_items.IUnique;

public abstract class BaseUniqueAxe extends ItemAxe implements IUnique {

    public BaseUniqueAxe() {
	IUnique.ITEMS.put(GUID(), this);
    }

    @Override
    public String slot() {
	return new Axe().GUID();
    }

}
