package com.robertx22.mine_and_slash.vanilla_mc.items;

import com.robertx22.mine_and_slash.database.base.CreativeTabs;
import net.minecraft.item.Item.Properties;

public class ItemDefault extends Properties {

    public ItemDefault() {
        this.maxStackSize(64);
        // this.defaultMaxDamage(0); max dmg sets stakc size to 1!!!
        this.group(CreativeTabs.MyModTab);

    }
}
