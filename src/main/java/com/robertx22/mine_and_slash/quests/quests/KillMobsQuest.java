package com.robertx22.mine_and_slash.quests.quests;

import com.robertx22.mine_and_slash.quests.base.BaseKillMobsQuest;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

public class KillMobsQuest extends BaseKillMobsQuest {

    @Override
    public float amountRequired() {
        return RandomUtils.RandomRange(40, 75);
    }

    @Override
    public String GUID() {
        return "kill_mobs";
    }
}
