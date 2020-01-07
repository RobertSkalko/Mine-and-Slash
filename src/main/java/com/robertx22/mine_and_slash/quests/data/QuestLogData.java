package com.robertx22.mine_and_slash.quests.data;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.ArrayList;
import java.util.List;

@Storable
public class QuestLogData {

    @Store
    public List<QuestSaveData> quests = new ArrayList<>();

}
