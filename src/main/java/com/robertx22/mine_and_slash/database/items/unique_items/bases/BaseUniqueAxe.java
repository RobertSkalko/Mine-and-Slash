package com.robertx22.mine_and_slash.database.items.unique_items.bases;

import com.robertx22.mine_and_slash.database.rarities.gears.UniqueGear;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemAxe;

public final class BaseUniqueAxe extends ItemAxe {

    public BaseUniqueAxe() {
        super(UniqueGear.getInstance()
            .Rank());
    }

}
