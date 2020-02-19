package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.runes.*;
import com.robertx22.mine_and_slash.database.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;

import java.util.ArrayList;
import java.util.List;

public class Runes implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<BaseRuneItem> All = new ArrayList<BaseRuneItem>() {
            {
                {
                    add(new CenItem(0));
                    add(new MosItem(0));
                    add(new ItaItem(0));
                    add(new BerItem(0));
                    add(new DosItem(0));
                    add(new GohItem(0));
                    add(new RahItem(0));
                    add(new VohItem(0));
                    add(new XahItem(0));
                    add(new AnoItem(0));

                }

            }
        };

        All.forEach(x -> x.registerToSlashRegistry());

    }
}
