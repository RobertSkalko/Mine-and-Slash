package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.cloth.ClothBoots;
import com.robertx22.mine_and_slash.database.gearitemslots.cloth.ClothChest;
import com.robertx22.mine_and_slash.database.gearitemslots.cloth.ClothHelmet;
import com.robertx22.mine_and_slash.database.gearitemslots.cloth.ClothPants;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Bracelet;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Charm;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Necklace;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Ring;
import com.robertx22.mine_and_slash.database.gearitemslots.leather.LeatherBoots;
import com.robertx22.mine_and_slash.database.gearitemslots.leather.LeatherChest;
import com.robertx22.mine_and_slash.database.gearitemslots.leather.LeatherHelmet;
import com.robertx22.mine_and_slash.database.gearitemslots.leather.LeatherPants;
import com.robertx22.mine_and_slash.database.gearitemslots.offhand.Shield;
import com.robertx22.mine_and_slash.database.gearitemslots.offhand.Torch;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlateBoots;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlateChest;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlateHelmet;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlatePants;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.*;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;

import java.util.ArrayList;
import java.util.List;

public class GearTypes implements ISlashRegistryInit {

    @Override
    public void registerAll() {
        List<GearItemSlot> All = new ArrayList<GearItemSlot>() {
            {
                {
                    add(LeatherChest.INSTANCE);
                    add(LeatherHelmet.INSTANCE);
                    add(LeatherPants.INSTANCE);
                    add(LeatherBoots.INSTANCE);

                    add(ClothChest.INSTANCE);
                    add(ClothHelmet.INSTANCE);
                    add(ClothPants.INSTANCE);
                    add(ClothBoots.INSTANCE);

                    add(PlateBoots.INSTANCE);
                    add(PlatePants.INSTANCE);
                    add(PlateHelmet.INSTANCE);
                    add(PlateChest.INSTANCE);

                    add(Shield.INSTANCE);
                    add(Torch.INSTANCE);

                    add(Ring.INSTANCE);
                    add(Necklace.INSTANCE);
                    add(Bracelet.INSTANCE);
                    add(Charm.INSTANCE);

                    add(Bow.INSTANCE);
                    add(Hammer.INSTANCE);
                    add(Staff.INSTANCE);
                    add(Axe.INSTANCE);
                    add(CrossBow.INSTANCE);
                    add(Wand.INSTANCE);
                    add(Sword.INSTANCE);
                    
                }

            }
        };

        All.forEach(x -> x.registerToSlashRegistry());

    }
}
