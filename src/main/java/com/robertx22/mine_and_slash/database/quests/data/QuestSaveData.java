package com.robertx22.mine_and_slash.database.quests.data;

import com.robertx22.mine_and_slash.database.quests.base.QuestResult;
import com.robertx22.mine_and_slash.database.quests.base.QuestReward;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.List;

@Storable
public class QuestSaveData {

    @Store
    public List<QuestTaskData> tasks = new ArrayList<>();

    @Store
    public QuestRewardData reward = new QuestRewardData();

    @Store
    public QuestResult questResult = QuestResult.UNFINISHED;

    public void onComplete(PlayerEntity player) {

        tasks.forEach(x -> x.getQuest().onCompleted(player, this));

        QuestReward questReward = SlashRegistry.QuestRewards().get(reward.rewardGUID);

        questReward.giveReward(player, this);
    }

    public void setupResult(PlayerEntity player) {

        if (questResult == QuestResult.UNFINISHED) {

            if (tasks.isEmpty()) {
                return;
            }

            tasks.forEach(x -> x.setupResult());

            boolean unfinished = false;
            boolean failed = false;

            for (QuestTaskData task : tasks) {
                if (task.questResult == QuestResult.FAILED) {
                    failed = true;
                }
                if (task.questResult == QuestResult.UNFINISHED) {
                    unfinished = true;
                }
            }

            if (failed) {
                questResult = QuestResult.FAILED;
            } else if (!unfinished) {
                questResult = QuestResult.COMPLETED;

                onComplete(player);

            }

        }

    }

    public int getProgressPercent() {

        float current = tasks.stream().mapToInt(x -> (int) x.getCurrentCompletition()).sum();
        float max = tasks.stream().mapToInt(x -> (int) x.amountRequired).sum();

        return (int) (current / max * 100);
    }
}
