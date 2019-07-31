package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.items.runes.base.BaseUniqueRuneItem;
import com.robertx22.mine_and_slash.database.items.runes.unique_runes.ONIItem;
import com.robertx22.mine_and_slash.database.items.runes.unique_runes.PSIItem;
import com.robertx22.mine_and_slash.database.items.runes.unique_runes.QARItem;
import com.robertx22.mine_and_slash.database.items.runes.unique_runes.WORItem;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;

import java.util.ArrayList;
import java.util.List;

public class UniqueRunes implements ISlashRegistryInit {

    @Override
    public void registerAll() {
        List<BaseUniqueRuneItem> All = new ArrayList<BaseUniqueRuneItem>() {
            {
                {
                    add(new PSIItem());
                    add(new QARItem());
                    add(new WORItem());
                    add(new ONIItem());
                }

            }
        };

        All.forEach(x -> x.registerToSlashRegistry());

    }

}
