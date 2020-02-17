package com.robertx22.mine_and_slash.database.items.unique_items.bases;

import com.robertx22.mine_and_slash.database.rarities.gears.UniqueGear;
import com.robertx22.mine_and_slash.items.gearitems.armor.plate.PlateHelmetItem;

public final class BaseUniqueHelmet extends PlateHelmetItem {

    public BaseUniqueHelmet() {
        super(UniqueGear.getInstance()
            .Rank());

    }

    @Override
    public boolean shouldRegisterLangName() {
        return true;
    }
}
