package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.database.quests.base.QuestReward;
import com.robertx22.mine_and_slash.database.quests.quest_rewards.MapQuestReward;

import java.util.ArrayList;
import java.util.List;

public class QuestRewards implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<QuestReward> All = new ArrayList<QuestReward>() {
            {
                {
                    add(MapQuestReward.INSTANCE);

                }
            }
        };

        All.forEach(x -> x.registerToSlashRegistry());

    }
}
