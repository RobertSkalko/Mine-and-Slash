package com.robertx22.mine_and_slash.quests.data;

import com.robertx22.mine_and_slash.database.loot_crates.bases.MapScoreEnum;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class QuestRewardData {

    @Store
    public int exp = 0;

    @Store
    public float multi = 1F;

    @Store
    public MapScoreEnum score = MapScoreEnum.AVERAGE;

    @Store
    public String rewardGUID = "";

}
