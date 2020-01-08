package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.quests.base.Quest;
import com.robertx22.mine_and_slash.quests.quests.KillMobsQuest;

import java.util.ArrayList;
import java.util.List;

public class Quests implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<Quest> All = new ArrayList<Quest>() {
            {
                {
                    add(KillMobsQuest.INSTANCE);

                }
            }
        };

        All.forEach(x -> x.registerToSlashRegistry());

    }
}
