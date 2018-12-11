package com.robertx22.database.stat_types;

import com.robertx22.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;

public class UnknownStat extends Stat {
    public static String GUID = "Renamed or Deleted Stat Error";

    public UnknownStat() {

    }

    @Override
    public String unlocString() {
	return "unknown_stat";
    }

    @Override
    public String Guid() {
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

}
