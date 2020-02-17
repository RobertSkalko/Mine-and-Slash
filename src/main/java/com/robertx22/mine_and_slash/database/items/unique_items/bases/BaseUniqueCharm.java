package com.robertx22.mine_and_slash.database.items.unique_items.bases;

import com.robertx22.mine_and_slash.database.rarities.gears.UniqueGear;
import com.robertx22.mine_and_slash.items.gearitems.baubles.ItemCharm;

public final class BaseUniqueCharm extends ItemCharm {
    public BaseUniqueCharm() {
        super(UniqueGear.getInstance()
            .Rank());
    }

}
