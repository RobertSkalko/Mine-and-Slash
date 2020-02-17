package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.item_modifications.bases.BaseItemModification;
import com.robertx22.mine_and_slash.database.item_modifications.gear_items.*;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;

import java.util.ArrayList;
import java.util.List;

public class ItemModifications implements ISlashRegistryInit {

    @Override
    public void registerAll() {
        List<BaseItemModification> All = new ArrayList<BaseItemModification>() {
            {
                {
                    add(new AddChaosStatMod());
                    add(new AddMajorArcanaMod());
                    add(new AddSetMod());
                    add(new BetterPrimaryStats());
                    add(new PerfectPrimaryStats());
                    add(new AddRarestAffixesMod());

                }

            }
        };

        All.forEach(x -> x.registerToSlashRegistry());
    }
}
