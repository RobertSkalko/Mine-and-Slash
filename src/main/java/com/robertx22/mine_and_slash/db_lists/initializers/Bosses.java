package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.bosses.base.Boss;
import com.robertx22.mine_and_slash.database.bosses.impl.HellLordBoss;
import com.robertx22.mine_and_slash.database.bosses.impl.NecromancerBoss;
import com.robertx22.mine_and_slash.database.bosses.impl.ReplicatorBoss;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;

import java.util.ArrayList;
import java.util.List;

public class Bosses implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<Boss> list = new ArrayList<>();

        list.add(NecromancerBoss.getInstance());
        list.add(HellLordBoss.getInstance());
        list.add(ReplicatorBoss.getInstance());

        list.forEach(x -> x.registerToSlashRegistry());

    }
}
