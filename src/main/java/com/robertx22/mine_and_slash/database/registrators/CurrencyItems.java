package com.robertx22.mine_and_slash.database.registrators;

import com.robertx22.mine_and_slash.database.data.currency.base.CurrencyItem;
import com.robertx22.exiled_lib.registry.ISlashRegistryInit;
import net.minecraftforge.registries.ForgeRegistries;

public class CurrencyItems implements ISlashRegistryInit {

    @Override
    public void registerAll() {
        ForgeRegistries.ITEMS.forEach(x -> {
            if (x instanceof CurrencyItem) {
                CurrencyItem cur = (CurrencyItem) x;
                cur.unregisterFromSlashRegistry();
                cur.registerToSlashRegistry();
            }
        });
    }

}
