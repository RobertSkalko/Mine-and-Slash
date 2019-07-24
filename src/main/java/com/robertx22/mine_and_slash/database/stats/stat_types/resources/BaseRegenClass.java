package com.robertx22.mine_and_slash.database.stats.stat_types.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public abstract class BaseRegenClass extends Stat {

    @Override
    public String locDescForLangFile() {
        return "Regen happens every few seconds but is also used for other stats or spells";
    }

    public BaseRegenClass() {

    }

    @Override
    public boolean ScalesToLevel() {
        return false;
    }

    @Override
    public Elements Element() {
        return null;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

}
