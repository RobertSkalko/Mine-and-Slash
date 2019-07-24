package com.robertx22.mine_and_slash.database.stats.stat_types.misc;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public class BonusExp extends Stat {
    @Override
    public String locDescForLangFile() {
        return "Increases exp gained";
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public String GUID() {
        return "bonusexpflat";
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
    public String locNameForLangFile() {
        return "Bonus Exp";
    }
}
