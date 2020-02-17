package com.robertx22.mine_and_slash.database.unique_items.bases;

import com.robertx22.mine_and_slash.database.rarities.gears.UniqueGear;
import com.robertx22.mine_and_slash.items.gearitems.armor.plate.PlatePantsItem;

public final class BaseUniquePantsItem extends PlatePantsItem {

    public BaseUniquePantsItem() {
        super(UniqueGear.getInstance()
            .Rank());

    }

    @Override
    public boolean shouldRegisterLangName() {
        return false;
    }
}
