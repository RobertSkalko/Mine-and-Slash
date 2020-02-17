package com.robertx22.mine_and_slash.database.items.unique_items.bases;

import com.robertx22.mine_and_slash.database.rarities.gears.UniqueGear;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemBow;

public class BaseUniqueBow extends ItemBow {

    public BaseUniqueBow() {
        super(UniqueGear.getInstance()
            .Rank());
    }

}
