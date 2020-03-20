package com.robertx22.mine_and_slash.items.reset_potions.bases;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import net.minecraft.item.Item;

public abstract class BasePotion extends Item implements IGUID, IAutoLocName {

    public BasePotion() {
        super(new Properties().maxStackSize(64)
            .group(CreativeTabs.MyModTab));

    }
}
