package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.cloth.ClothSlippers;
import com.robertx22.mine_and_slash.database.gearitemslots.cloth.OccultistRobes;
import com.robertx22.mine_and_slash.database.gearitemslots.cloth.SilkPants;
import com.robertx22.mine_and_slash.database.gearitemslots.cloth.SorcererCirclet;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.LifeNecklace;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.OccultNecklace;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.OccultRing;
import com.robertx22.mine_and_slash.database.gearitemslots.offhand.Buckler;
import com.robertx22.mine_and_slash.database.gearitemslots.offhand.SpiritShield;
import com.robertx22.mine_and_slash.database.gearitemslots.offhand.TowerShield;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.IronChestplate;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.IronGreaves;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.IronHelmet;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.IronLegguards;
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

                    add(IronGreaves.INSTANCE);
                    add(IronLegguards.INSTANCE);
                    add(IronHelmet.INSTANCE);
                    add(IronChestplate.INSTANCE);

                    add(ClothSlippers.INSTANCE);
                    add(OccultistRobes.INSTANCE);
                    add(SorcererCirclet.INSTANCE);
                    add(SilkPants.INSTANCE);

                    add(TowerShield.INSTANCE);
                    add(SpiritShield.INSTANCE);
                    add(Buckler.INSTANCE);

                    add(OccultRing.INSTANCE);
                    add(LifeNecklace.INSTANCE);
                    add(OccultNecklace.INSTANCE);

                    add(HunterBow.INSTANCE);
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
