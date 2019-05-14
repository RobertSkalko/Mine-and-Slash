package com.robertx22.items.unique_items.bases;

import com.robertx22.database.gearitemslots.Sword;
import com.robertx22.items.gearitems.weapons.ItemSword;
import com.robertx22.items.unique_items.IUnique;

public abstract class BaseUniqueSword extends ItemSword implements IUnique {
    public BaseUniqueSword() {
	IUnique.ITEMS.put(GUID(), this);
    }

    @Override
    public String slot() {
	return new Sword().GUID();
    }
}
