package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.currency.base.CurrencyItem;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;
import net.minecraftforge.registries.ForgeRegistries;

public class CurrencyItems implements ISlashRegistryInit {

    @Override
    public void registerAll() {
        ForgeRegistries.ITEMS.forEach(x -> {
            if (x instanceof CurrencyItem) {
                CurrencyItem cur = (CurrencyItem) x;
                cur.registerToSlashRegistry();
            }
        });
    }

}
