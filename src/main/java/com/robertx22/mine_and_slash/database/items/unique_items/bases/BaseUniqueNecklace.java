package com.robertx22.mine_and_slash.database.items.unique_items.bases;

import com.robertx22.mine_and_slash.database.rarities.gears.UniqueGear;
import com.robertx22.mine_and_slash.items.gearitems.baubles.ItemNecklace;

public class BaseUniqueNecklace extends ItemNecklace {
    public BaseUniqueNecklace() {
        super(UniqueGear.getInstance()
            .Rank());
    }

}
