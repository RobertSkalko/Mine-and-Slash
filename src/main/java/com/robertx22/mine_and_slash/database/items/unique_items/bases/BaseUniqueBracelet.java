package com.robertx22.mine_and_slash.database.items.unique_items.bases;

import com.robertx22.mine_and_slash.database.rarities.gears.UniqueGear;
import com.robertx22.mine_and_slash.items.gearitems.baubles.ItemBracelet;

public final class BaseUniqueBracelet extends ItemBracelet {

    public BaseUniqueBracelet() {
        super(UniqueGear.getInstance()
            .Rank());
    }

}
