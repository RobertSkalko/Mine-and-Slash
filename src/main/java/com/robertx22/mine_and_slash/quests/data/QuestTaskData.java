package com.robertx22.mine_and_slash.quests.data;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class QuestTaskData {

    @Store
    public String questGUID = "";

    @Store
    public float currentCompletition = 0;

    @Store
    public float amountRequired = 0;

    @Store
    public QuestResult questResult = QuestResult.UNFINISHED;

}
