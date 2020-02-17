package com.robertx22.mine_and_slash.database.items.unique_items.bases;

import com.robertx22.mine_and_slash.database.rarities.gears.UniqueGear;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemHammer;

public final class BaseUniqueHammer extends ItemHammer {

    public BaseUniqueHammer() {
        super(UniqueGear.getInstance()
            .Rank());
    }

    @Override
    public boolean shouldRegisterLangName() {
        return true;
    }

}
