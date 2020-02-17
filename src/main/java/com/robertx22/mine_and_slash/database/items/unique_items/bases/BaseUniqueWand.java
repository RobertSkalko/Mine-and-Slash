package com.robertx22.mine_and_slash.database.items.unique_items.bases;

import com.robertx22.mine_and_slash.database.rarities.gears.UniqueGear;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemWand;

public class BaseUniqueWand extends ItemWand {

    public BaseUniqueWand() {
        super(UniqueGear.getInstance()
            .Rank());
    }

}