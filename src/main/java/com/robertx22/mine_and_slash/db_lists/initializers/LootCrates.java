package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.loot_crates.*;
import com.robertx22.mine_and_slash.database.loot_crates.bases.LootCrate;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;

import java.util.ArrayList;
import java.util.List;

public class LootCrates implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<LootCrate> All = new ArrayList<LootCrate>() {
            {
                {

                    add(NormalCrate.getInstance());

                    add(UniqueCrate.INSTANCE);
                    add(CommonerCrate.INSTANCE);
                    add(CurrencyCrate.INSTANCE);
                    add(RuneCrate.INSTANCE);

                    add(MythicCrate.INSTANCE);
                    add(ArmorcrafterCrate.INSTANCE);
                    add(WeaponCraftersCrate.INSTANCE);
                    add(JewerlyCrafterCrate.INSTANCE);

                    add(CartographerCrate.getInstance());

                    add(RequirementCrate.INT);
                    add(RequirementCrate.WIS);
                    add(RequirementCrate.STR);
                    add(RequirementCrate.STA);
                    add(RequirementCrate.VIT);
                    add(RequirementCrate.DEX);

                }
            }
        };

        All.forEach(x -> x.registerToSlashRegistry());

    }
}
