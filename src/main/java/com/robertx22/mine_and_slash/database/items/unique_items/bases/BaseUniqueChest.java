package com.robertx22.mine_and_slash.database.items.unique_items.bases;

import com.robertx22.mine_and_slash.database.rarities.gears.UniqueGear;
import com.robertx22.mine_and_slash.items.gearitems.armor.plate.PlateChestItem;

public class BaseUniqueChest extends PlateChestItem {

    public BaseUniqueChest() {
        super(UniqueGear.getInstance()
            .Rank());

    }

}
