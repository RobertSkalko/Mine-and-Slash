package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.gearitemslots.*;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.leather.LeatherBoots;
import com.robertx22.mine_and_slash.database.gearitemslots.leather.LeatherChest;
import com.robertx22.mine_and_slash.database.gearitemslots.leather.LeatherHelmet;
import com.robertx22.mine_and_slash.database.gearitemslots.leather.LeatherPants;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;

import java.util.ArrayList;
import java.util.List;

public class GearTypes implements ISlashRegistryInit {

    @Override
    public void registerAll() {
        List<GearItemSlot> All = new ArrayList<GearItemSlot>() {
            {
                {

                    add(new LeatherChest());
                    add(new LeatherHelmet());
                    add(new LeatherPants());
                    add(new LeatherBoots());

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
                    add(new CrossBow());

                }

            }
        };

        All.forEach(x -> x.registerToSlashRegistry());

    }
}
