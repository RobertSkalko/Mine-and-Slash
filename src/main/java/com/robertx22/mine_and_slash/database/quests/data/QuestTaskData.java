package com.robertx22.mine_and_slash.database.quests.data;

import com.robertx22.mine_and_slash.database.quests.base.Quest;
import com.robertx22.mine_and_slash.database.quests.base.QuestResult;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class QuestTaskData {

    @Store
    public String questGUID = "";

    @Store
    private float currentCompletition = 0;

    @Store
    public int amountRequired = 100000;

    @Store
    public QuestResult questResult = QuestResult.UNFINISHED;

    public Quest getQuest() {
        return SlashRegistry.Quests().get(questGUID);
    }

    public boolean canProgress() {
        return questResult == QuestResult.UNFINISHED;
    }

    private boolean isCompleted() {
        return currentCompletition >= amountRequired;
    }

    public void setupResult() {
        if (questResult == QuestResult.UNFINISHED) {
            if (isCompleted()) {
                questResult = QuestResult.COMPLETED;
            }
        }
    }

    public int getCurrentCompletition() {
        return (int) this.currentCompletition;
    }

    public void increaseCompletition(float add) {
        if (canProgress()) {
            this.currentCompletition += add;
        }
    }
}
