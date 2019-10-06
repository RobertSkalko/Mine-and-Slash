package com.robertx22.mine_and_slash.database.stats.stat_types.game_changers;

import com.robertx22.mine_and_slash.database.stats.stat_types.BaseTrait;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public abstract class BaseGameChangerTrait extends BaseTrait {

    @Override
    public Elements Element() {
        return null;
    }

    @Override
    public int Weight() {
        return 0; // NEVER DROP, these should be from talent tree only
    }

    @Override
    public boolean IsShownOnStatGui() {
        return true;
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.Misc;
    }

}


