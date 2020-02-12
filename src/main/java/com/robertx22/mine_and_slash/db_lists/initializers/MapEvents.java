package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.map_events.base.MapEvent;
import com.robertx22.mine_and_slash.database.map_events.impl.SkeletonHordeEvent;
import com.robertx22.mine_and_slash.database.map_events.impl.SpiderHordeEvent;
import com.robertx22.mine_and_slash.database.map_events.impl.ZombieHordeEvent;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;

import java.util.ArrayList;
import java.util.List;

public class MapEvents implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<MapEvent> list = new ArrayList<>();

        list.add(ZombieHordeEvent.getInstance());
        list.add(SpiderHordeEvent.getInstance());
        list.add(SkeletonHordeEvent.getInstance());

        list.forEach(x -> x.registerToSlashRegistry());
    }
}
