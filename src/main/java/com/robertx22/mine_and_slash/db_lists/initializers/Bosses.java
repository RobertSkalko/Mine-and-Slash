package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.bosses.base.Boss;
import com.robertx22.mine_and_slash.database.bosses.impl.NecromancerBoss;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;

import java.util.ArrayList;
import java.util.List;

public class Bosses implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<Boss> list = new ArrayList<>();

        list.add(new NecromancerBoss());

        list.forEach(x -> x.registerToSlashRegistry());

    }
}
