package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.unique_items.jewelry.necklace.BirthingMiracleNecklace;
import com.robertx22.mine_and_slash.database.unique_items.jewelry.necklace.SkullOfSpiritsNecklace;
import com.robertx22.mine_and_slash.database.unique_items.weapons.sword.WaterElementalSword;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;

public class UniqueGears implements ISlashRegistryInit {
    @Override
    public void registerAll() {

        new WaterElementalSword().addToSerializables();
        new BirthingMiracleNecklace().addToSerializables();
        new SkullOfSpiritsNecklace().addToSerializables();

    }
}
