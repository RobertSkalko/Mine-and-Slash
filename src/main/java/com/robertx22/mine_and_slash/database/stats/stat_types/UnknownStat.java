package com.robertx22.mine_and_slash.database.stats.stat_types;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public class UnknownStat extends Stat {
    public static String GUID = "Renamed or Deleted Stat Error";

    public UnknownStat() {

    }

    @Override
    public boolean IsShownOnTooltip() {
        return false;
    }

    @Override
    public String locDescForLangFile() {
        return "";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public boolean ScalesToLevel() {
        return true;
    }

    @Override
    public Elements Element() {
        return null;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public String locNameForLangFile() {
        return "Unknown";
    }
}
