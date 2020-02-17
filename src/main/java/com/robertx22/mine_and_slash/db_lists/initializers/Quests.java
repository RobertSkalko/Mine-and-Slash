package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.quests.base.Quest;
import com.robertx22.mine_and_slash.database.quests.quests.CollectRarityPointsKillQuest;
import com.robertx22.mine_and_slash.database.quests.quests.KillOnlyOfRarityOrHigherQuest;
import com.robertx22.mine_and_slash.database.quests.quests.OpenAnyCratesQuest;
import com.robertx22.mine_and_slash.database.quests.quests.SimpleKillMobsQuest;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;

import java.util.ArrayList;
import java.util.List;

public class Quests implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<Quest> All = new ArrayList<Quest>() {
            {
                {
                    add(SimpleKillMobsQuest.INSTANCE);

                    add(KillOnlyOfRarityOrHigherQuest.EPIC);
                    add(KillOnlyOfRarityOrHigherQuest.LEGENDARY);
                    add(KillOnlyOfRarityOrHigherQuest.MYTHIC);

                    add(CollectRarityPointsKillQuest.INSTANCE);

                    add(OpenAnyCratesQuest.getInstance());

                }
            }
        };

        All.forEach(x -> x.registerToSlashRegistry());

    }
}
