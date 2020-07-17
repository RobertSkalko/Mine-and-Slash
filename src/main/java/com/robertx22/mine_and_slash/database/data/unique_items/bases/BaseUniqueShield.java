package com.robertx22.mine_and_slash.database.data.unique_items.bases;

import net.minecraft.item.ShieldItem;

public final class BaseUniqueShield extends ShieldItem {

    public BaseUniqueShield() {
        super(new Properties().maxStackSize(1)
            .defaultMaxDamage(1000));

    }

}

