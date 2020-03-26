package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public abstract class BaseGameChangerTrait extends Trait {

    @Override
    public Elements getElement() {
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

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList();
    }
}


