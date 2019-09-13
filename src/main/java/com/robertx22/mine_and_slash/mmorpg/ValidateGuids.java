package com.robertx22.mine_and_slash.mmorpg;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class ValidateGuids {
    public static void validate() {

        for (Item item : ForgeRegistries.ITEMS) {

            if (item instanceof ISlashRegistryEntry) {
                IGUID guid = (IGUID) item;

                if (!item.getRegistryName().getPath().equals(guid.GUID())) {
                    System.out.println("Guid doesn't match: " + item.getRegistryName()
                            .toString());
                }

            }

        }

    }
}
