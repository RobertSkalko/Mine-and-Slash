package com.robertx22.mine_and_slash.database.stats.types.misc;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public class BonusExp extends Stat {

    public static String GUID = "bonusexpflat";

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
        return GUID;
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
