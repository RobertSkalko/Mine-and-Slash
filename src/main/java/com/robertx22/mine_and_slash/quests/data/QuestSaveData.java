package com.robertx22.mine_and_slash.quests.data;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.ArrayList;
import java.util.List;

@Storable
public class QuestSaveData {

    @Store
    public List<QuestTaskData> tasks = new ArrayList<>();

    @Store
    public QuestResult questResult = QuestResult.UNFINISHED;

}
