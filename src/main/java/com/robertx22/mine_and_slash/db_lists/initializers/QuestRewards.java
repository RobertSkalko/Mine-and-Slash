package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.quests.base.QuestReward;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;

import java.util.ArrayList;
import java.util.List;

public class QuestRewards implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<QuestReward> All = new ArrayList<QuestReward>() {
            {
                {

                }
            }
        };

        All.forEach(x -> x.registerToSlashRegistry());

    }
}
