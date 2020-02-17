package com.robertx22.mine_and_slash.database.items.unique_items.bases;

import com.robertx22.mine_and_slash.database.rarities.gears.UniqueGear;
import com.robertx22.mine_and_slash.items.gearitems.baubles.ItemRing;

public final class BaseUniqueRing extends ItemRing {
    public BaseUniqueRing() {
        super(UniqueGear.getInstance()
            .Rank());
    }

    @Override
    public boolean shouldRegisterLangName() {
        return true;
    }
}
