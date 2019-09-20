package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.gearitemslots.*;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.cloth.ClothBoots;
import com.robertx22.mine_and_slash.database.gearitemslots.cloth.ClothChest;
import com.robertx22.mine_and_slash.database.gearitemslots.cloth.ClothHelmet;
import com.robertx22.mine_and_slash.database.gearitemslots.cloth.ClothPants;
import com.robertx22.mine_and_slash.database.gearitemslots.leather.LeatherBoots;
import com.robertx22.mine_and_slash.database.gearitemslots.leather.LeatherChest;
import com.robertx22.mine_and_slash.database.gearitemslots.leather.LeatherHelmet;
import com.robertx22.mine_and_slash.database.gearitemslots.leather.LeatherPants;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlateBoots;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlateChest;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlateHelmet;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlatePants;
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

                    add(new ClothChest());
                    add(new ClothHelmet());
                    add(new ClothPants());
                    add(new ClothBoots());

                    add(new PlateBoots());
                    add(new PlatePants());
                    add(new PlateHelmet());
                    add(new PlateChest());

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
