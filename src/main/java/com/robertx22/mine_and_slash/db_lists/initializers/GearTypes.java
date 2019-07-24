package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.gearitemslots.*;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;

import java.util.ArrayList;
import java.util.List;

public class GearTypes implements ISlashRegistryInit {

    @Override
    public void registerAll() {
        List<GearItemSlot> All = new ArrayList<GearItemSlot>() {
            {
                {
                    add(new Boots());
                    add(new Pants());
                    add(new Helmet());
                    add(new Chest());
                    add(new Ring());
                    add(new Sword());
                    add(new Necklace());
                    add(new Bracelet());
                    add(new Bow());
                    add(new Charm());
                    add(new Hammer());
                    add(new Staff());
                    add(new Axe());
                    add(new Shield());
                    add(new Torch());

                }

            }
        };

        All.forEach(x -> SlashRegistry.getRegistry(SlashRegistryType.GEAR_TYPE)
                .register(x));

    }
}
