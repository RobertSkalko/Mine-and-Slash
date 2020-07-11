package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.LifeNecklace;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.OccultNecklace;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.OccultRing;
import com.robertx22.mine_and_slash.database.gearitemslots.offhand.ArmorShield;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlateBoots;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlateChest;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlateHelmet;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlatePants;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.*;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;

import java.util.ArrayList;
import java.util.List;

public class GearTypes implements ISlashRegistryInit {

    @Override
    public void registerAll() {
        List<GearItemSlot> All = new ArrayList<GearItemSlot>() {
            {
                {

                    add(PlateBoots.INSTANCE);
                    add(PlatePants.INSTANCE);
                    add(PlateHelmet.INSTANCE);
                    add(PlateChest.INSTANCE);

                    add(ArmorShield.INSTANCE);

                    add(OccultRing.INSTANCE);
                    add(LifeNecklace.INSTANCE);
                    add(OccultNecklace.INSTANCE);

                    add(Bow.INSTANCE);
                    add(Staff.INSTANCE);
                    add(Axe.INSTANCE);
                    add(Crossbow.INSTANCE);
                    add(Sword.INSTANCE);

                }

            }
        };

        All.forEach(x -> x.registerToSlashRegistry());

    }
}
